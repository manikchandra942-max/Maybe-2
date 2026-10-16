package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CyanGlow
import com.example.ui.theme.EmeraldSEO
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.YouTubeRed

data class SeoTip(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val proTip: String
)

@Composable
fun SeoGuideScreen(modifier: Modifier = Modifier) {
    val tips = listOf(
        SeoTip(
            title = "১. ইউটিউব অ্যালগরিদমের প্রথম ২৪ ঘণ্টার গুরুত্ব",
            icon = Icons.Default.AutoGraph,
            description = "গান আপলোড করার প্রথম ২৪ ঘণ্টার ক্লিক থ্রু রেট (CTR) এবং এভারেজ ভিউ ডিউরেশন (AVD) নির্ধারণ করে গানটি হোমফিডে কত কোটি মানুষের কাছে পৌঁছাবে।",
            proTip = "গান রিলিজের দিনই সোশ্যাল মিডিয়া ও কমিউনিটি ট্যাবে একসাথে প্রিমিয়ার লিঙ্ক শেয়ার করুন।"
        ),
        SeoTip(
            title = "২. বাংলা গানের জন্য সেরা রিলিজ টাইম",
            icon = Icons.Default.AccessTime,
            description = "বাংলাদেশ ও ভারতের পশ্চিমবঙ্গ অঞ্চলের মিউজিক শ্রোতারা সবচেয়ে বেশি সক্রিয় থাকেন বৃহস্পতিবার ও শুক্রবার সন্ধ্যা ৬:০০টা থেকে রাত ৯:০০টার মধ্যে।",
            proTip = "ভিডিও রিলিজের অন্তত ২ ঘণ্টা আগে আনলিস্টেড অবস্থায় আপলোড করে HD প্রসেসিং সম্পূর্ণ হতে দিন।"
        ),
        SeoTip(
            title = "৩. হাই-কনভার্টিং থাম্বনেইল রুলস",
            icon = Icons.Default.Image,
            description = "মোবাইল স্ক্রিনে থাম্বনেইল খুব ছোট দেখায়। তাই থাম্বনেইলে কখনোই পুরো প্যারাগ্রাফ লিখবেন না। সর্বোচ্চ ৩ থেকে ৪টি বড় ও আকর্ষণীয় শব্দ ব্যবহার করুন।",
            proTip = "মুখের অভিব্যক্তিতে স্পষ্ট আবেগ (কান্না/হাসি/রহস্য) থাকলে সাধারণ থাম্বনেইলের চেয়ে দ্বিগুণ ক্লিক আসে।"
        ),
        SeoTip(
            title = "৪. ডেসক্রিপশনের প্রথম ৩ লাইনের রহস্য",
            icon = Icons.Default.TipsAndUpdates,
            description = "ইউটিউব ভিউয়াররা 'Show more' চাপার আগেই প্রথম ৩ লাইন দেখতে পায়। এই ৩ লাইনে গানটির মূল হুক ও একটি স্ট্রং কল-টু-অ্যাকশন রাখুন।",
            proTip = "লিরিক্স সম্পূর্ণ ডেসক্রিপশনে দিলে গুগলে সার্চ করা হাজারো মানুষ আপনার ভিডিও খুঁজে পাবে।"
        ),
        SeoTip(
            title = "৫. শর্টস ও রিলস ফানেল স্ট্র্যাটেজি",
            icon = Icons.Default.MusicVideo,
            description = "গানের সবচেয়ে ক্যাচি ১৫-৩০ সেকেন্ডের অন্তরা বা মুখড়া দিয়ে ৫-১০টি আলাদা শর্টস ও রিলস তৈরি করুন। প্রতিটি শর্টসের অডিও ট্র্যাকে মূল গান লিঙ্ক করুন।",
            proTip = "শর্টসের ভিউয়ারকে লং-ফর্ম মিউজিক ভিডিওতে পাঠাতে ইউটিউবের 'Related Video' ফিচার ব্যবহার করুন।"
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeader(
                title = "ইউটিউব মিউজিক সাকসেস গাইড",
                subtitle = "নতুন গান রিলিজের সেরা কৌশল ও অ্যালগরিদম ট্রিকস"
            )
        }

        items(tips.size) { index ->
            val tip = tips[index]
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = StudioSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = tip.icon,
                            contentDescription = null,
                            tint = when (index % 3) {
                                0 -> YouTubeRed
                                1 -> NeonPurple
                                else -> CyanGlow
                            },
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = tip.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = tip.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextMuted,
                            lineHeight = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldSEO,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "প্রো-টিপ: ${tip.proTip}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = EmeraldSEO,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
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
