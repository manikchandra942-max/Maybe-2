package com.example.data.api

import android.util.Log
import com.example.BuildConfig
import com.example.data.engine.OfflineMusicStrategyEngine
import com.example.data.model.GeneratedStrategy
import com.example.data.model.SocialTeaserData
import com.example.data.model.SongInput
import com.example.data.model.ThumbnailConcept
import com.example.data.model.TitleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiAiStrategyService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun generateStrategy(
        input: SongInput,
        customApiKey: String? = null
    ): GeneratedStrategy = withContext(Dispatchers.IO) {
        val apiKey = customApiKey?.trim()?.ifEmpty { null }
            ?: runCatching { BuildConfig.GEMINI_API_KEY }.getOrNull()?.trim()

        if (apiKey.isNullOrEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            Log.d("GeminiStrategy", "No valid API key found. Using high-performance offline strategy engine.")
            return@withContext OfflineMusicStrategyEngine.generateStrategy(input)
        }

        try {
            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

            val promptText = """
                You are a world-class YouTube Strategist and Music Marketing Expert for Bengali, Hindi and South Asian songs.
                Analyze the following song details and generate a comprehensive YouTube release and marketing strategy matching the 5 required deliverables.
                
                Song Information:
                - Title / Working Title: ${input.title}
                - Lyrics: 
                ${input.lyrics}
                - Mood: ${input.mood}
                - Genre: ${input.genre}
                - Artist / Singer: ${input.artist}
                - Composer: ${input.composer}
                - Lyricist: ${input.lyricist}
                - Channel / Label: ${input.channelName}
                - Language: ${input.language}
                - Release Type: ${input.releaseType}
                
                Required Deliverables:
                1. 5 High-CTR and SEO-friendly YouTube titles (5 different angles: emotional hook, SEO search volume, viral curiosity, official music video format, lyrical poetic).
                2. A complete, rich, copy-paste-ready YouTube description formatted with hooks, emotional synopsis, credits block, full lyrics, timestamps, natural SEO keyword paragraph, and hashtags.
                3. High-ranking YouTube tags in comma-separated format (<500 characters total).
                4. 3 Eye-catching thumbnail ideas (visual scene, color palette, mobile text overlay, expression, lighting).
                5. Social media promotion teasers (Instagram/Shorts caption, 3 viral 0-5s audio hooks, WhatsApp status text, Call to Action).

                Format your response strictly as valid JSON with this structure:
                {
                   "titles": [
                      {"title": "...", "angle": "...", "whyItWorks": "..."}
                   ],
                   "description": "...",
                   "tags": ["tag1", "tag2", ...],
                   "tagsCommaSeparated": "...",
                   "thumbnailConcepts": [
                      {"conceptTitle": "...", "visualDescription": "...", "colorPalette": "...", "textOverlayText": "...", "facialExpression": "...", "lightingStyle": "..."}
                   ],
                   "socialTeasers": {
                      "reelsShortsCaption": "...",
                      "viralAudioHooks": ["hook1", "hook2", "hook3"],
                      "whatsappStatusText": "...",
                      "callToAction": "..."
                   }
                }
            """.trimIndent()

            val requestJson = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().put("text", promptText))
                        })
                    })
                })
                put("generationConfig", JSONObject().apply {
                    put("responseMimeType", "application/json")
                    put("temperature", 0.7)
                })
            }

            val requestBody = requestJson.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (!response.isSuccessful || responseBody.isNullOrEmpty()) {
                Log.w("GeminiStrategy", "API call failed with code: ${response.code}. Falling back to offline engine.")
                return@withContext OfflineMusicStrategyEngine.generateStrategy(input)
            }

            val rootJson = JSONObject(responseBody)
            val candidates = rootJson.optJSONArray("candidates")
            val textPart = candidates?.optJSONObject(0)
                ?.optJSONObject("content")
                ?.optJSONArray("parts")
                ?.optJSONObject(0)
                ?.optString("text")

            if (textPart.isNullOrEmpty()) {
                Log.w("GeminiStrategy", "Empty candidate text. Falling back to offline engine.")
                return@withContext OfflineMusicStrategyEngine.generateStrategy(input)
            }

            parseGeminiOutput(textPart, input)
        } catch (e: Exception) {
            Log.e("GeminiStrategy", "Error in Gemini API generation: ${e.message}", e)
            OfflineMusicStrategyEngine.generateStrategy(input)
        }
    }

    private fun parseGeminiOutput(jsonText: String, input: SongInput): GeneratedStrategy {
        val cleanJson = jsonText.trim()
            .removePrefix("```json")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()

        val obj = JSONObject(cleanJson)

        val titlesList = mutableListOf<TitleItem>()
        val titlesArray = obj.optJSONArray("titles")
        if (titlesArray != null) {
            for (i in 0 until titlesArray.length()) {
                val t = titlesArray.getJSONObject(i)
                titlesList.add(
                    TitleItem(
                        title = t.optString("title"),
                        angle = t.optString("angle"),
                        characterCount = t.optString("title").length,
                        scoreRating = "৯৯%",
                        whyItWorks = t.optString("whyItWorks")
                    )
                )
            }
        }

        val tagsList = mutableListOf<String>()
        val tagsArray = obj.optJSONArray("tags")
        if (tagsArray != null) {
            for (i in 0 until tagsArray.length()) {
                tagsList.add(tagsArray.getString(i))
            }
        }

        val thumbnailList = mutableListOf<ThumbnailConcept>()
        val thumbArray = obj.optJSONArray("thumbnailConcepts")
        if (thumbArray != null) {
            for (i in 0 until thumbArray.length()) {
                val c = thumbArray.getJSONObject(i)
                thumbnailList.add(
                    ThumbnailConcept(
                        conceptTitle = c.optString("conceptTitle"),
                        visualDescription = c.optString("visualDescription"),
                        colorPalette = c.optString("colorPalette"),
                        textOverlayText = c.optString("textOverlayText"),
                        facialExpression = c.optString("facialExpression"),
                        lightingStyle = c.optString("lightingStyle")
                    )
                )
            }
        }

        val teasersObj = obj.optJSONObject("socialTeasers")
        val viralHooks = mutableListOf<String>()
        val hooksArray = teasersObj?.optJSONArray("viralAudioHooks")
        if (hooksArray != null) {
            for (i in 0 until hooksArray.length()) {
                viralHooks.add(hooksArray.getString(i))
            }
        }

        val socialTeasers = SocialTeaserData(
            reelsShortsCaption = teasersObj?.optString("reelsShortsCaption") ?: "",
            viralAudioHooks = viralHooks,
            whatsappStatusText = teasersObj?.optString("whatsappStatusText") ?: "",
            callToAction = teasersObj?.optString("callToAction") ?: ""
        )

        val description = obj.optString("description")
        val tagsComma = obj.optString("tagsCommaSeparated").ifEmpty { tagsList.joinToString(", ") }

        // If any list is empty, fallback to offline engine for that part
        val fallback = OfflineMusicStrategyEngine.generateStrategy(input)

        return GeneratedStrategy(
            songTitle = input.title.ifEmpty { "নতুন গান" },
            artistName = input.artist.ifEmpty { "কণ্ঠশিল্পী" },
            mood = input.mood,
            genre = input.genre,
            language = input.language,
            lyricsSnippet = input.lyrics.take(150),
            titles = if (titlesList.isNotEmpty()) titlesList else fallback.titles,
            fullDescription = if (description.isNotEmpty()) description else fallback.fullDescription,
            tags = if (tagsList.isNotEmpty()) tagsList else fallback.tags,
            tagsCommaSeparated = if (tagsComma.isNotEmpty()) tagsComma else fallback.tagsCommaSeparated,
            thumbnailConcepts = if (thumbnailList.isNotEmpty()) thumbnailList else fallback.thumbnailConcepts,
            socialTeasers = if (socialTeasers.reelsShortsCaption.isNotEmpty()) socialTeasers else fallback.socialTeasers
        )
    }
}
