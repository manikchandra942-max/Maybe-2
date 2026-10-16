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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GeneratedStrategy
import com.example.ui.components.CopyChip
import com.example.ui.components.MockYouTubeThumbnailCard
import com.example.ui.components.QuickCopyIconButton
import com.example.ui.components.SectionHeader
import com.example.ui.components.StatBadge
import com.example.ui.components.copyToClipboard
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
fun StrategyResultScreen(
    strategy: GeneratedStrategy?,
    selectedSectionIndex: Int,
    onSectionChange: (Int) -> Unit,
    onSaveStrategy: () -> Unit,
    onBackToInput: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    if (strategy == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = NeonPurple,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "এখনও কোনো স্ট্র্যাটেজি তৈরি করা হয়নি",
                    style = MaterialTheme.typography.titleMedium.copy(color = TextPrimary),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "প্রথমে গানের নাম ও লিরিক্স দিয়ে 'মার্কেটিং প্ল্যান তৈরি করুন' বাটনে চাপুন।",
                    style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Button(
                    onClick = onBackToInput,
                    colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                ) {
                    Text("গানের তথ্য ইনপুট করুন")
                }
            }
        }
        return
    }

    val sectionTabs = listOf(
        Pair("১. শিরোনাম", Icons.Default.Title),
        Pair("২. ডেসক্রিপশন", Icons.Default.Description),
        Pair("৩. ট্যাগ", Icons.AutoMirrored.Filled.Label),
        Pair("৪. থাম্বনেইল", Icons.Default.Image),
        Pair("৫. সোশ্যাল টিজার", Icons.Default.Campaign)
    )

    Column(modifier = modifier.fillMaxSize()) {
        // Song Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = StudioSurfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = YouTubeRed,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "YouTube SEO Expert",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = strategy.mood,
                            style = MaterialTheme.typography.labelSmall.copy(color = CyanGlow)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "\"${strategy.songTitle}\" - ${strategy.artistName}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Button(
                        onClick = onSaveStrategy,
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("save_strategy_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkAdd,
                            contentDescription = "Save",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("সেভ করুন", fontSize = 12.sp)
                    }
                }
            }
        }

        // Section Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedSectionIndex,
            containerColor = StudioSurface,
            contentColor = NeonPurple,
            edgePadding = 12.dp
        ) {
            sectionTabs.forEachIndexed { index, pair ->
                val isSelected = selectedSectionIndex == index
                Tab(
                    selected = isSelected,
                    onClick = { onSectionChange(index) },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = pair.second,
                                contentDescription = pair.first,
                                modifier = Modifier.size(16.dp),
                                tint = if (isSelected) NeonPurple else TextMuted
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = pair.first,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) NeonPurple else TextMuted
                                )
                            )
                        }
                    }
                )
            }
        }

        // Tab Content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            when (selectedSectionIndex) {
                0 -> TitlesSection(strategy, onShowMessage)
                1 -> DescriptionSection(strategy, onShowMessage)
                2 -> TagsSection(strategy, onShowMessage)
                3 -> ThumbnailsSection(strategy)
                4 -> SocialTeasersSection(strategy, onShowMessage)
            }
        }
    }
}

@Composable
private fun TitlesSection(
    strategy: GeneratedStrategy,
    onShowMessage: (String) -> Unit
) {
    val context = LocalContext.current
    val allTitlesText = strategy.titles.mapIndexed { idx, it -> "${idx + 1}. ${it.title}" }.joinToString("\n\n")

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    title = "১. পাঁচটি আকর্ষণীয় ও এসইও-ফ্রেন্ডলি শিরোনাম",
                    subtitle = "অ্যালগরিদম অপ্টিমাইজড, হাই-CTR ও কি-ওয়ার্ড সমৃদ্ধ"
                )
                CopyChip(
                    textToCopy = allTitlesText,
                    label = "সব কপি",
                    onCopied = { onShowMessage("সব শিরোনাম কপি করা হয়েছে!") }
                )
            }
        }

        itemsIndexed(strategy.titles) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("title_card_$index"),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(NeonPurple.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = NeonPurple,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = StudioSurfaceHighlight
                            ) {
                                Text(
                                    text = item.angle,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = CyanGlow,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            StatBadge(
                                label = "অক্ষর",
                                value = "${item.characterCount}/100",
                                color = if (item.characterCount in 40..75) EmeraldSEO else NeonPurple
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            QuickCopyIconButton(
                                textToCopy = item.title,
                                contentDescription = "Copy title",
                                onCopied = { onShowMessage("শিরোনামটি কপি করা হয়েছে!") }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            lineHeight = 22.sp
                        )
                    )

                    if (item.whyItWorks.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = StudioSurfaceVariant,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💡 কেন কাজ করবে: ${item.whyItWorks}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextMuted,
                                    fontSize = 11.sp
                                ),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DescriptionSection(
    strategy: GeneratedStrategy,
    onShowMessage: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    title = "২. সম্পূর্ণ তথ্যবহুল ভিডিও ডেসক্রিপশন",
                    subtitle = "ইউটিউব এসইও, ক্রেডিট ও লিরিক্স দিয়ে প্রস্তুতকৃত"
                )
                CopyChip(
                    textToCopy = strategy.fullDescription,
                    label = "সম্পূর্ণ ডেসক্রিপশন কপি",
                    onCopied = { onShowMessage("সম্পূর্ণ ডেসক্রিপশন কপি হয়েছে!") }
                )
            }
        }

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
                        Text(
                            text = "রেডি-টু-পেস্ট ডেসক্রিপশন বক্স",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = CyanGlow,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "${strategy.fullDescription.length} অক্ষর",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = StudioSurfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = strategy.fullDescription,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextPrimary,
                                lineHeight = 20.sp,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                            ),
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsSection(
    strategy: GeneratedStrategy,
    onShowMessage: (String) -> Unit
) {
    val charCount = strategy.tagsCommaSeparated.length

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    title = "৩. এসইও এর জন্য প্রয়োজনীয় ট্যাগ",
                    subtitle = "ইউটিউব স্টুডিওর ট্যাগ বক্সে সরাসরি পেস্ট করুন"
                )
                CopyChip(
                    textToCopy = strategy.tagsCommaSeparated,
                    label = "কমা-সেপারেটেড কপি",
                    onCopied = { onShowMessage("ট্যাগগুলো কপি করা হয়েছে!") }
                )
            }
        }

        // Character limit tracker (YouTube 500 limit)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ইউটিউব ট্যাগ ক্যারেক্টার লিমিট:",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
                        )
                        Text(
                            text = "$charCount / ৫০০ অক্ষর",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (charCount <= 500) EmeraldSEO else YouTubeRed
                            )
                        )
                    }

                    Surface(
                        color = if (charCount <= 500) EmeraldSEO.copy(alpha = 0.2f) else YouTubeRed.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (charCount <= 500) "✅ ইউটিউব অপ্টিমাইজড" else "⚠️ ৫০০ এর বেশি",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (charCount <= 500) EmeraldSEO else YouTubeRed,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Tag Badges
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "মোট ট্যাগ সংখ্যা: ${strategy.tags.size} টি",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = NeonPurple,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        strategy.tags.forEach { tag ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = StudioSurfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "#",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = YouTubeRed,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = tag,
                                        style = MaterialTheme.typography.labelSmall.copy(color = TextPrimary)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ThumbnailsSection(
    strategy: GeneratedStrategy
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SectionHeader(
                title = "৪. নজরকাড়া থাম্বনেইল আইডিয়া বা কনসেপ্ট",
                subtitle = "ক্লিক থ্রু রেট (CTR) বৃদ্ধির জন্য প্রফেশনাল ডিজাইন ফ্রেম"
            )
        }

        itemsIndexed(strategy.thumbnailConcepts) { index, concept ->
            MockYouTubeThumbnailCard(
                conceptTitle = concept.conceptTitle,
                textOverlay = concept.textOverlayText,
                visualScene = concept.visualDescription,
                colorPalette = concept.colorPalette,
                channelName = "YouTube Music"
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SocialTeasersSection(
    strategy: GeneratedStrategy,
    onShowMessage: (String) -> Unit
) {
    val teaser = strategy.socialTeasers

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    title = "৫. সোশ্যাল মিডিয়ায় প্রচারের জন্য সংক্ষিপ্ত টিজার",
                    subtitle = "Reels, Shorts, TikTok ও WhatsApp স্ট্যাটাস প্রমোশন"
                )
                CopyChip(
                    textToCopy = "${teaser.reelsShortsCaption}\n\n${teaser.whatsappStatusText}",
                    label = "সব কপি",
                    onCopied = { onShowMessage("সোশ্যাল টিজার কপি করা হয়েছে!") }
                )
            }
        }

        // Reels / Shorts Caption
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
                        Text(
                            text = "📱 রিলস ও শর্টস ক্যাপশন (Instagram / YouTube Shorts)",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = NeonPurple,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        QuickCopyIconButton(
                            textToCopy = teaser.reelsShortsCaption,
                            contentDescription = "Copy Reels Caption",
                            onCopied = { onShowMessage("ক্যাপশন কপি হয়েছে!") }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = StudioSurfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = teaser.reelsShortsCaption,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextPrimary,
                                lineHeight = 20.sp
                            ),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        // Viral 0-5s Audio Hooks
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "🔥 প্রথম ৫ সেকেন্ডের ভাইরাল হুক (Viral Audio Hooks):",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = YouTubeRed,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    teaser.viralAudioHooks.forEach { hook ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            color = StudioSurfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = hook,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                QuickCopyIconButton(
                                    textToCopy = hook,
                                    onCopied = { onShowMessage("হুক কপি হয়েছে!") }
                                )
                            }
                        }
                    }
                }
            }
        }

        // WhatsApp Status
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
                        Text(
                            text = "💬 হোয়াটসঅ্যাপ / ফেসবুক স্ট্যাটাস টেক্সট:",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = EmeraldSEO,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        QuickCopyIconButton(
                            textToCopy = teaser.whatsappStatusText,
                            onCopied = { onShowMessage("স্ট্যাটাস টেক্সট কপি হয়েছে!") }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = StudioSurfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = teaser.whatsappStatusText,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextPrimary,
                                lineHeight = 18.sp
                            ),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
