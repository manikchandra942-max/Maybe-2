package com.example.data.local

import com.example.data.model.GeneratedStrategy
import com.example.data.model.SocialTeaserData
import com.example.data.model.ThumbnailConcept
import com.example.data.model.TitleItem
import org.json.JSONArray
import org.json.JSONObject

object StrategyJsonMapper {

    fun toEntity(strategy: GeneratedStrategy): StrategyEntity {
        val titlesArray = JSONArray()
        strategy.titles.forEach { t ->
            val obj = JSONObject()
            obj.put("title", t.title)
            obj.put("angle", t.angle)
            obj.put("characterCount", t.characterCount)
            obj.put("scoreRating", t.scoreRating)
            obj.put("whyItWorks", t.whyItWorks)
            titlesArray.put(obj)
        }

        val tagsArray = JSONArray()
        strategy.tags.forEach { tagsArray.put(it) }

        val thumbnailsArray = JSONArray()
        strategy.thumbnailConcepts.forEach { c ->
            val obj = JSONObject()
            obj.put("conceptTitle", c.conceptTitle)
            obj.put("visualDescription", c.visualDescription)
            obj.put("colorPalette", c.colorPalette)
            obj.put("textOverlayText", c.textOverlayText)
            obj.put("facialExpression", c.facialExpression)
            obj.put("lightingStyle", c.lightingStyle)
            thumbnailsArray.put(obj)
        }

        val teasersObj = JSONObject()
        teasersObj.put("reelsShortsCaption", strategy.socialTeasers.reelsShortsCaption)
        val hooksArray = JSONArray()
        strategy.socialTeasers.viralAudioHooks.forEach { hooksArray.put(it) }
        teasersObj.put("viralAudioHooks", hooksArray)
        teasersObj.put("whatsappStatusText", strategy.socialTeasers.whatsappStatusText)
        teasersObj.put("callToAction", strategy.socialTeasers.callToAction)

        return StrategyEntity(
            id = strategy.id,
            songTitle = strategy.songTitle,
            artistName = strategy.artistName,
            mood = strategy.mood,
            genre = strategy.genre,
            language = strategy.language,
            lyricsSnippet = strategy.lyricsSnippet,
            titlesJson = titlesArray.toString(),
            fullDescription = strategy.fullDescription,
            tagsJson = tagsArray.toString(),
            tagsCommaSeparated = strategy.tagsCommaSeparated,
            thumbnailConceptsJson = thumbnailsArray.toString(),
            socialTeasersJson = teasersObj.toString(),
            createdAt = strategy.createdAt
        )
    }

    fun toDomain(entity: StrategyEntity): GeneratedStrategy {
        val titlesList = mutableListOf<TitleItem>()
        try {
            val arr = JSONArray(entity.titlesJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                titlesList.add(
                    TitleItem(
                        title = obj.optString("title"),
                        angle = obj.optString("angle"),
                        characterCount = obj.optInt("characterCount"),
                        scoreRating = obj.optString("scoreRating", "98%"),
                        whyItWorks = obj.optString("whyItWorks")
                    )
                )
            }
        } catch (_: Exception) {}

        val tagsList = mutableListOf<String>()
        try {
            val arr = JSONArray(entity.tagsJson)
            for (i in 0 until arr.length()) {
                tagsList.add(arr.getString(i))
            }
        } catch (_: Exception) {}

        val thumbnailsList = mutableListOf<ThumbnailConcept>()
        try {
            val arr = JSONArray(entity.thumbnailConceptsJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                thumbnailsList.add(
                    ThumbnailConcept(
                        conceptTitle = obj.optString("conceptTitle"),
                        visualDescription = obj.optString("visualDescription"),
                        colorPalette = obj.optString("colorPalette"),
                        textOverlayText = obj.optString("textOverlayText"),
                        facialExpression = obj.optString("facialExpression"),
                        lightingStyle = obj.optString("lightingStyle")
                    )
                )
            }
        } catch (_: Exception) {}

        var socialTeasers = SocialTeaserData(
            reelsShortsCaption = "",
            viralAudioHooks = emptyList(),
            whatsappStatusText = "",
            callToAction = ""
        )
        try {
            val obj = JSONObject(entity.socialTeasersJson)
            val hooks = mutableListOf<String>()
            val hooksArr = obj.optJSONArray("viralAudioHooks")
            if (hooksArr != null) {
                for (i in 0 until hooksArr.length()) {
                    hooks.add(hooksArr.getString(i))
                }
            }
            socialTeasers = SocialTeaserData(
                reelsShortsCaption = obj.optString("reelsShortsCaption"),
                viralAudioHooks = hooks,
                whatsappStatusText = obj.optString("whatsappStatusText"),
                callToAction = obj.optString("callToAction")
            )
        } catch (_: Exception) {}

        return GeneratedStrategy(
            id = entity.id,
            songTitle = entity.songTitle,
            artistName = entity.artistName,
            mood = entity.mood,
            genre = entity.genre,
            language = entity.language,
            lyricsSnippet = entity.lyricsSnippet,
            titles = titlesList,
            fullDescription = entity.fullDescription,
            tags = tagsList,
            tagsCommaSeparated = entity.tagsCommaSeparated,
            thumbnailConcepts = thumbnailsList,
            socialTeasers = socialTeasers,
            createdAt = entity.createdAt
        )
    }
}
