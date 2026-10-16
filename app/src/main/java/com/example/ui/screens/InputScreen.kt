package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SampleSongPresets
import com.example.data.model.SongInput
import com.example.ui.components.AudioEqualizerVisualizer
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CyanGlow
import com.example.ui.theme.EmeraldSEO
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceHighlight
import com.example.ui.theme.StudioSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.YouTubeRed

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputScreen(
    songInput: SongInput,
    isGenerating: Boolean,
    customApiKey: String,
    onTitleChange: (String) -> Unit,
    onLyricsChange: (String) -> Unit,
    onMoodChange: (String) -> Unit,
    onGenreChange: (String) -> Unit,
    onArtistChange: (String) -> Unit,
    onComposerChange: (String) -> Unit,
    onLyricistChange: (String) -> Unit,
    onChannelChange: (String) -> Unit,
    onReleaseTypeChange: (String) -> Unit,
    onLoadPreset: (SongInput) -> Unit,
    onClearInput: () -> Unit,
    onApiKeyChange: (String) -> Unit,
    onGenerate: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showApiKeyField by remember { mutableStateOf(false) }

    val moodOptions = listOf(
        "বিরহ ও কষ্ট / Sad",
        "রোমান্টিক / Romantic",
        "ফোক ফিউশন / Folk Fusion",
        "আধুনিক মেলোডি / Melody",
        "নস্টালজিক / Nostalgic",
        "হিপ-হপ ও র‍্যাপ / Hip Hop",
        "সুফি ও আধ্যাত্মিক / Sufi",
        "উৎসব ও পার্টি / Upbeat"
    )

    val releaseTypeOptions = listOf(
        "অফিসিয়াল মিউজিক ভিডিও (Official Music Video)",
        "অফিসিয়াল লিরিক্যাল ভিডিও (Lyrical Video)",
        "লাইভ স্টুডিও সেশন (Studio Session)",
        "অ্যাকোস্টিক আনপ্লাগড (Acoustic Unplugged)",
        "শর্টস / রিল স্পেশাল (Shorts/Reels Audio)"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        1.dp,
                        Brush.linearGradient(listOf(YouTubeRed, NeonPurple, CyanGlow)),
                        RoundedCornerShape(20.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = StudioSurfaceVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        Brush.linearGradient(listOf(YouTubeRed, NeonPurple)),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MusicNote,
                                    contentDescription = "Music",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "ইউটিউব মিউজিক এক্সপার্ট",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                )
                                Text(
                                    text = "SEO Strategist for Songs & Lyrics",
                                    style = MaterialTheme.typography.labelSmall.copy(color = CyanGlow)
                                )
                            }
                        }

                        AudioEqualizerVisualizer(barCount = 8)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "গানের লিরিক্স বা মুড শেয়ার করুন—এসইও শিরোনাম, ফুল ডেসক্রিপশন, ৫০০ ক্যারেক্টার ট্যাগ, থাম্বনেইল কনসেপ্ট ও রিলস টিজার তৈরি হবে নিমিষেই!",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextMuted,
                            lineHeight = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // API Key Settings Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { showApiKeyField = !showApiKeyField }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Key,
                                contentDescription = "API Key",
                                tint = if (customApiKey.isNotBlank()) EmeraldSEO else NeonPurple,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (showApiKeyField) "এপিআই কি সেটিংস লুকান" else "Gemini API Key (ঐচ্ছিক)",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (customApiKey.isNotBlank()) EmeraldSEO else NeonPurple,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        Surface(
                            color = StudioSurfaceHighlight,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Auto AI + Offline Engine",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    color = EmeraldSEO,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    if (showApiKeyField) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = customApiKey,
                            onValueChange = onApiKeyChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("custom_api_key_input"),
                            label = { Text("Gemini API Key (যদি থাকে)") },
                            placeholder = { Text("AI Studio Secrets অথবা কাস্টম কি...") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )
                    }
                }
            }
        }

        // Demo Presets Quick Bar
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "দ্রুত ডেমো গান লোড করুন:",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    TextButton(onClick = onClearInput) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("ক্লিন করুন", style = MaterialTheme.typography.labelSmall)
                    }
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    items(SampleSongPresets.presets) { preset ->
                        val isSelected = songInput.title == preset.title
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) NeonPurple.copy(alpha = 0.25f) else StudioSurface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) NeonPurple else BorderSubtle
                            ),
                            modifier = Modifier
                                .clickable { onLoadPreset(preset) }
                                .testTag("preset_${preset.title}")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LibraryMusic,
                                    contentDescription = null,
                                    tint = if (isSelected) NeonPurple else CyanGlow,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = preset.title,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary
                                        )
                                    )
                                    Text(
                                        text = preset.genre,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 10.sp,
                                            color = TextMuted
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Song Title & Artist
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(
                        title = "১. গানের সাধারণ তথ্য",
                        subtitle = "ইউটিউবে র‍্যাংকিং এবং ব্র্যান্ডিংয়ের জন্য মূল তথ্য"
                    )

                    OutlinedTextField(
                        value = songInput.title,
                        onValueChange = onTitleChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("song_title_input"),
                        label = { Text("গানের নাম (Song Title)") },
                        placeholder = { Text("যেমন: তুমি হীনা / মাটির মেঠোপথ") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NeonPurple,
                            unfocusedBorderColor = BorderSubtle
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = songInput.artist,
                            onValueChange = onArtistChange,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("artist_input"),
                            label = { Text("কণ্ঠশিল্পী (Singer)") },
                            placeholder = { Text("শিল্পীর নাম") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )

                        OutlinedTextField(
                            value = songInput.channelName,
                            onValueChange = onChannelChange,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("channel_input"),
                            label = { Text("চ্যানেল / লেবেল") },
                            placeholder = { Text("লেবেল নাম") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = songInput.lyricist,
                            onValueChange = onLyricistChange,
                            modifier = Modifier.weight(1f),
                            label = { Text("গীতিকার (Lyricist)") },
                            placeholder = { Text("গীতিকার") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )

                        OutlinedTextField(
                            value = songInput.composer,
                            onValueChange = onComposerChange,
                            modifier = Modifier.weight(1f),
                            label = { Text("সুরকার (Composer)") },
                            placeholder = { Text("সুরকার") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )
                    }
                }
            }
        }

        // Mood & Genre Chips
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    SectionHeader(
                        title = "২. গানের মুড ও ক্যাটাগরি",
                        subtitle = "মুডের উপর নির্ভর করে ইউটিউব টাইটেল ও থাম্বনেইলের রঙ নির্ধারিত হবে"
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        moodOptions.forEach { mood ->
                            val selected = songInput.mood == mood
                            FilterChip(
                                selected = selected,
                                onClick = { onMoodChange(mood) },
                                label = { Text(mood, fontSize = 12.sp) },
                                leadingIcon = if (selected) {
                                    {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                } else null,
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NeonPurple,
                                    selectedLabelColor = Color.White,
                                    containerColor = StudioSurfaceVariant,
                                    labelColor = TextPrimary
                                ),
                                modifier = Modifier.testTag("mood_chip_${mood.hashCode()}")
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "রিলিজ ফরম্যাট নির্বাচন:",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextMuted,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        releaseTypeOptions.forEach { rType ->
                            val selected = songInput.releaseType == rType
                            FilterChip(
                                selected = selected,
                                onClick = { onReleaseTypeChange(rType) },
                                label = { Text(rType, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = YouTubeRed,
                                    selectedLabelColor = Color.White,
                                    containerColor = StudioSurfaceVariant,
                                    labelColor = TextPrimary
                                )
                            )
                        }
                    }
                }
            }
        }

        // Lyrics Text Area
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionHeader(
                            title = "৩. গানের সম্পূর্ণ বা আংশিক লিরিক্স",
                            subtitle = "লিরিক্স থেকে মূল কি-ওয়ার্ড ও আবেগ বের করে আনা হবে"
                        )
                    }

                    OutlinedTextField(
                        value = songInput.lyrics,
                        onValueChange = onLyricsChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .testTag("lyrics_input"),
                        placeholder = {
                            Text(
                                "এখানে আপনার গানের লিরিক্স পেস্ট করুন...\n\nযেমন:\nযদি কোনোদিন মনে পড়ে আমায়...\nখুঁজে নিও ওই দূর সীমানায়...",
                                color = TextMuted.copy(alpha = 0.7f),
                                fontSize = 13.sp
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NeonPurple,
                            unfocusedBorderColor = BorderSubtle
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "শব্দ সংখ্যা: ${songInput.lyrics.split("\\s+".toRegex()).filter { it.isNotBlank() }.size} টি",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
                        )
                        Text(
                            text = "অক্ষর: ${songInput.lyrics.length}",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
                        )
                    }
                }
            }
        }

        // Big Action Button
        item {
            Button(
                onClick = onGenerate,
                enabled = !isGenerating,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("generate_strategy_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = YouTubeRed,
                    contentColor = Color.White
                )
            ) {
                if (isGenerating) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.5.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "ইউটিউব স্ট্র্যাটেজি তৈরি হচ্ছে...",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "Generate",
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "🚀 ৫টি মার্কেটিং প্ল্যান তৈরি করুন",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
