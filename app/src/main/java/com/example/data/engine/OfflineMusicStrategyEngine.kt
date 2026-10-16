package com.example.data.engine

import com.example.data.model.GeneratedStrategy
import com.example.data.model.SocialTeaserData
import com.example.data.model.SongInput
import com.example.data.model.ThumbnailConcept
import com.example.data.model.TitleItem

object OfflineMusicStrategyEngine {

    fun generateStrategy(input: SongInput): GeneratedStrategy {
        val title = input.title.trim().ifEmpty { "নতুন গান" }
        val artist = input.artist.trim().ifEmpty { "জনপ্রিয় কণ্ঠশিল্পী" }
        val mood = input.mood.trim()
        val genre = input.genre.trim()
        val channel = input.channelName.trim().ifEmpty { "Official Music Channel" }
        val composer = input.composer.trim().ifEmpty { artist }
        val lyricist = input.lyricist.trim().ifEmpty { "জনপ্রিয় গীতিকার" }

        val firstLyricLine = input.lyrics.lines()
            .map { it.trim() }
            .firstOrNull { it.isNotEmpty() } ?: "হৃদয় ছোঁয়া সুর"

        val isSad = mood.contains("বিরহ") || mood.contains("কষ্ট") || mood.contains("Sad") || genre.contains("Sad")
        val isFolk = mood.contains("ফোক") || mood.contains("Folk") || genre.contains("Folk") || genre.contains("বাউল")
        val isRomantic = mood.contains("রোমান্টিক") || mood.contains("Romantic") || mood.contains("ভালোবাসা")

        // 1. ৫টি আকর্ষণীয় এবং এসইও-ফ্রেন্ডলি ইউটিউব শিরোনাম
        val titles = if (isSad) {
            listOf(
                TitleItem(
                    title = "$title - $artist | যে গান চোখ ভেজাবে | Bangla Sad Song 2026 | Official Music Video",
                    angle = "আবেগঘন ও ভাইরাল হুক",
                    whyItWorks = "শ্রোতার ইমোশন ও সাম্প্রতিক বছরের কীওয়ার্ড টার্গেট করে সর্বোচ্চ CTR অর্জন করে।"
                ),
                TitleItem(
                    title = "$title | $artist | \"$firstLyricLine\" | New Bengali Emotional Song",
                    angle = "লিরিক্যাল সার্চ এসইও",
                    whyItWorks = "গানের প্রথম আকর্ষণীয় লিরিক্স দিয়ে যারা সার্চ করবে তাদের সরাসরি আকৃষ্ট করবে।"
                ),
                TitleItem(
                    title = "$title (অফিসিয়াল ভিডিও) - $artist | বুকভাঙ্গা বিরহের গান | Bangla New Song 2026",
                    angle = "হাই-কনভার্টিং ভাইরাল ফরম্যাট",
                    whyItWorks = "ইউটিউব হোম ও সাজেস্টেড ফিডে বিরহের গান খোঁজা শ্রোতাদের দ্রুত ক্লিক করতে বাধ্য করবে।"
                ),
                TitleItem(
                    title = "$artist - $title | $lyricist | $composer | Official Lyrical Video",
                    angle = "ক্রেডিট ও অ্যালগরিদম ফ্রেন্ডলি",
                    whyItWorks = "শিল্পী এবং অফিসিয়াল ব্র্যান্ড খোঁজার জন্য ইউটিউব সার্চ অ্যালগরিদমে শীর্ষে থাকবে।"
                ),
                TitleItem(
                    title = "হারিয়ে যাওয়া ভালোবাসার নীরব কান্না... $title | $artist | Hit Bangla Melody",
                    angle = "স্টোরিটেলিং ও কৌতূহল হুক",
                    whyItWorks = "গল্পের মতো শিরোনাম শ্রোতার কৌতূহল বহুগুণে বাড়িয়ে তোলে।"
                )
            )
        } else if (isFolk) {
            listOf(
                TitleItem(
                    title = "$title - $artist | মাটির টানে প্রাণের গান | New Bangla Folk Fusion 2026",
                    angle = "ফোক হেরিটেজ ও ড্রাইভ",
                    whyItWorks = "বাংলা ফোক গানপ্রেমী দেশ ও প্রবাসের শ্রোতাদের নিখুঁতভাবে কানেক্ট করবে।"
                ),
                TitleItem(
                    title = "$title | $artist | মন জুড়ানো সুরের মায়াজাল | Official Folk Music Video",
                    angle = "মনকাড়া আবেগ ও ভাইব",
                    whyItWorks = "পজিটিভ ও প্রশান্তিদায়ক শব্দের ব্যবহার ভিউয়ার রিটেনশন বাড়ায়।"
                ),
                TitleItem(
                    title = "$title ($artist) | বাংলার খাঁটি সুরের ফিউশন | Bangla New Song 2026",
                    angle = "ফিউশন ও সার্চ এসইও",
                    whyItWorks = "নতুন ও তরুণ প্রজন্মের ফিউশন মিউজিকপ্রেমীদের টার্গেট করে তৈরি।"
                ),
                TitleItem(
                    title = "যে সুর শুনলে মন ভালো হয়ে যায় | $title - $artist | Folk Studio Session",
                    angle = "ভাইরাল ফিল-গুড হুক",
                    whyItWorks = "ইউটিউব রিকমেন্ডেশনে সাধারণ দর্শককে ক্লিক করানোর জাদুকরী ফরম্যাট।"
                ),
                TitleItem(
                    title = "$artist - $title | কথা ও সুর: $lyricist | Official Music Video",
                    angle = "অফিসিয়াল আর্ট ও অরিজিনাল",
                    whyItWorks = "অরিজিনাল মিউজিক ভিডিও হিসেবে ইউটিউব কন্টেন্ট আইডিতে নিখুঁত র‌্যাংক পায়।"
                )
            )
        } else {
            listOf(
                TitleItem(
                    title = "$title - $artist | হৃদয় ছোঁয়া রোমান্টিক গান | Bangla Romantic Song 2026",
                    angle = "রোমান্টিক এসইও হুক",
                    whyItWorks = "রোমান্টিক গান সার্চের প্রধান কি-ফ্রেজগুলোকে এক সাথে সংযুক্ত করে।"
                ),
                TitleItem(
                    title = "$title | $artist | ভালোবাসার নতুন অনুভূতি | Official Music Video 2026",
                    angle = "ভাইরাল ড্রিম ভাইব",
                    whyItWorks = "ক্লিক থ্রু রেট (CTR) বহুগুণ বৃদ্ধি করে তরুণ যুগলদের কানেক্ট করে।"
                ),
                TitleItem(
                    title = "\"$firstLyricLine\" | $title - $artist | New Bengali Love Song",
                    angle = "লিরিক সার্চ অপ্টিমাইজড",
                    whyItWorks = "সোশ্যাল মিডিয়া থেকে সুর শুনে সার্চ করা শ্রোতারা গানটি সহজেই খুঁজে পাবে।"
                ),
                TitleItem(
                    title = "$title (অফিসিয়াল গান) - $artist ft. $composer | Trending Bangla Song",
                    angle = "ট্রেন্ডিং ও কোলাবরেশন",
                    whyItWorks = "ইউটিউব ট্রেন্ডিং অ্যালগরিদম ও সার্চ রেজাল্টে অগ্রাধিকার পেতে সাহায্য করবে।"
                ),
                TitleItem(
                    title = "মনের সবটুকু ভালোবাসা দিয়ে সাজানো... $title | $artist | Heart Touching Song",
                    angle = "আবেগঘন স্টোরিটেলিং",
                    whyItWorks = "হোমফিডে থাম্বনেইলের সাথে এই শিরোনাম ক্লিক পাওয়ার নিশ্চয়তা বাড়ায়।"
                )
            )
        }

        // 2. ভিডিওর জন্য একটি বিস্তারিত এবং তথ্যবহুল ডেসক্রিপশন
        val lyricsFormatted = input.lyrics.trim().ifEmpty {
            """$title
কণ্ঠ: $artist
কথা: $lyricist
সুর ও সঙ্গীত: $composer"""
        }

        val description = buildString {
            appendLine("🎶 \"$title\" - $artist | Official Music Release 2026")
            appendLine("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            appendLine("✨ ভালোবাসার এবং অনুভূতির গভীরতম সুর নিয়ে হাজির হলো নতুন গান \"$title\"।")
            appendLine("মন ছুঁয়ে যাওয়া সুর, বাস্তব জীবনের ছোঁয়া এবং $artist-এর জাদুকরী কণ্ঠ গানটিকে করে তুলেছে অনন্য। আশা করি গানটি আপনাদের হৃদয়ে দীর্ঘস্থায়ী স্থান করে নেবে। গানটি ভালো লাগলে লাইক, কমেন্ট ও বন্ধুদের সাথে শেয়ার করতে ভুলবেন না!")
            appendLine()
            appendLine("🎧 অডিও স্ট্রিমিং ও সাবস্ক্রাইব করুন:")
            appendLine("👉 চ্যানেলটি সাবস্ক্রাইব করে বেল আইকন বাজিয়ে রাখুন: https://youtube.com/@$channel")
            appendLine("👉 Spotify / Apple Music / Amazon Music: Coming Soon")
            appendLine()
            appendLine("📋 অফিসিয়াল ক্রেডিট (Official Song Credits):")
            appendLine("• গান (Song): $title")
            appendLine("• কণ্ঠ (Singer): $artist")
            appendLine("• গীতিকার (Lyrics): $lyricist")
            appendLine("• সুর ও সঙ্গীত (Composition & Music): $composer")
            appendLine("• জনরা ও মুড (Genre & Mood): $genre ($mood)")
            appendLine("• প্রযোজনা ও লেবেল (Label): $channel")
            appendLine("• পোস্ট প্রডাকশন ও কালার: Studio Visuals")
            appendLine()
            appendLine("⏱️ টাইমস্ট্যাম্পস (Timestamps):")
            appendLine("0:00 - ইন্ট্রো (Intro & Prelude)")
            appendLine("0:25 - মুখড়া (Mukhra / Opening)")
            appendLine("1:15 - অন্তরা ১ (First Antara)")
            appendLine("2:10 - ইন্টারলুড মিউজিক (Interlude)")
            appendLine("2:45 - সমাপনী ও ফাইনাল মেলোডি (Outro)")
            appendLine()
            appendLine("📜 সম্পূর্ণ লিরিক্স (Full Lyrics):")
            appendLine("----------------------------------------")
            appendLine(lyricsFormatted)
            appendLine("----------------------------------------")
            appendLine()
            appendLine("🔍 ইউটিউব সার্চ কীওয়ার্ড (SEO Keywords Summary):")
            appendLine("Bangla new song 2026, $title bangla song, $artist new song, $title $artist official video, bangla romantic song, bangla sad song, bangla lyrical video, new music video bangladesh, $channel music.")
            appendLine()
            appendLine("📢 কপিরাইট সতর্কতা (Copyright Notice):")
            appendLine("All rights reserved by $channel. Unauthorized copying, reproduction, or distribution of this audio-visual content is strictly prohibited.")
            appendLine()
            appendLine("🏷️ অফিসিয়াল হ্যাশট্যাগ (Official Hashtags):")
            appendLine("#${title.replace(" ", "")} #$artist #${title.replace(" ", "")}Song #BanglaSong #NewBanglaSong2026 #BanglaMusicVideo #ViralBanglaSong #BanglaSong2026 #HitMusic #${channel.replace(" ", "")}")
        }

        // 3. এসইও এর জন্য প্রয়োজনীয় ট্যাগ (YouTube Studio Compatible Comma-Separated)
        val cleanTitle = title.replace(",", "").replace("|", "").trim()
        val cleanArtist = artist.replace(",", "").trim()
        val tagsList = listOf(
            cleanTitle,
            "$cleanTitle song",
            "$cleanTitle bangla song",
            "$cleanTitle $cleanArtist",
            cleanArtist,
            "$cleanArtist new song",
            "$cleanArtist bangla song 2026",
            "bangla new song 2026",
            "bangla song 2026",
            if (isSad) "bangla sad song" else "bangla romantic song",
            if (isSad) "new bangla sad song" else "bangla love song",
            "$cleanTitle official music video",
            "$cleanTitle lyrical video",
            "bangla music video 2026",
            "bangladeshi new song",
            "kolkata bangla song",
            "$channel new song",
            "heart touching bangla song",
            "viral bangla song",
            "$cleanArtist all songs",
            "new bengali song 2026"
        )
        val tagsComma = tagsList.joinToString(", ")

        // 4. ভিডিওর জন্য নজরকাড়া থাম্বনেইল আইডিয়া বা কনসেপ্ট
        val thumbnailConcepts = listOf(
            ThumbnailConcept(
                conceptTitle = "কনসেপ্ট ১: সিনেমাটিক ইমোশনাল ক্লোজ-আপ (Cinematic Emotion)",
                visualDescription = "শিল্পীর বা মডেলের গভীর আবেগভরা অভিব্যক্তিপূর্ণ ক্লোজ-আপ শট। হালকা বৃষ্টির ফোঁটা বা মেঠোপথ/জানালার ব্যাকগ্রাউন্ডে ঝাপসা নিয়ন বোকেহ আলো।",
                colorPalette = if (isSad) "গাঢ় নীল (Deep Midnight Navy), নিয়ন পার্পল ও গোল্ডেন অরেঞ্জ লাইটিং" else "উষ্ণ সূর্যাস্তের রঙ (Sunset Gold), পিচ ও টিল (Teal & Orange)",
                textOverlayText = if (isSad) "বুকভাঙ্গা বিরহ... \"$title\"" else "হৃদয় ছোঁয়া সুর: \"$title\"",
                facialExpression = "চোখে না বলা কথা, গভীর আত্মমগ্নতা ও আবেগময় অভিব্যক্তি",
                lightingStyle = "রিম লাইটিং (Rim Lighting) ও ড্রামাটিক ব্যাকলাইট যা অবয়বকে নিখুঁতভাবে ফুটিয়ে তোলে।"
            ),
            ThumbnailConcept(
                conceptTitle = "কনসেপ্ট ২: ড্রামাটিক স্টোরিটেলিং ফ্রেম (Storytelling Scene)",
                visualDescription = "মিউজিক ভিডিওর প্রধান গল্পের শীর্ষ মুহূর্ত—দুজনের বিচ্ছেদ বা মিলনের এক নজরকাড়া দৃশ্য। ব্যাকগ্রাউন্ডে প্রাকৃতিক বা শহুরে পরিবেশ।",
                colorPalette = "হাই-কনট্রাস্ট ক্রিয়েটিভ গ্রেডিং (Moody Shadows with Vibrant Highlights)",
                textOverlayText = "\"যে সুর কাঁদাবে\" | $cleanArtist",
                facialExpression = "দৃঢ় আকর্ষণীয় দৃষ্টি যা স্ক্রল করতে থাকা দর্শককে অবিলম্বে থামিয়ে দেয়।",
                lightingStyle = "সিনেমাটিক শ্যাডো প্লে এবং সফট কি-লাইট (Soft Key Light)।"
            ),
            ThumbnailConcept(
                conceptTitle = "কনসেপ্ট ৩: বোল্ড টাইপোগ্রাফি ও মিউজিক্যাল আইকন (Bold Typography)",
                visualDescription = "শিল্পী মাইক্রোফোনের সামনে বা গিটার হাতে স্টুডিও সিলুয়েট ভঙ্গিমায়। ফ্রেমের একপাশে বড় ও স্পষ্ট বাংলা ফন্টে গানের নাম।",
                colorPalette = "রয়েল ব্লু, উজ্জ্বল লাল ও গোল্ডেন ইয়েলো টেক্সট আউটলাইন",
                textOverlayText = "$title | $cleanArtist | NEW 2026",
                facialExpression = "আত্মবিশ্বাসী ও শিল্পমগ্ন অভিব্যক্তি",
                lightingStyle = "স্টুডিও নিয়ন গ্লো (Neon Studio Glow) ও ব্যাকড্রপ স্পটলাইট।"
            )
        )

        // 5. সোশ্যাল মিডিয়ায় প্রচারের জন্য সংক্ষিপ্ত টিজার
        val socialTeasers = SocialTeaserData(
            reelsShortsCaption = """🎶 "$title" এবার আপনার হৃদয়ে দোলা দিতে আসছে! 
🎤 কণ্ঠে: $cleanArtist | কথা ও সুর: $lyricist / $composer

পুরো ভিডিওটি দেখুন আমাদের অফিসিয়াল ইউটিউব চ্যানেলে। 
🔗 লিংক কমেন্টে ও বায়োতে দেওয়া আছে!
ভালো লাগলে শেয়ার করতে ভুলবেন না। ❤️

#$cleanTitle #$cleanArtist #BanglaSong #BanglaReels #MusicLovers #NewSong2026 #BanglaMusic""",
            viralAudioHooks = listOf(
                "🔥 হুক ১ (০-৫ সেকেন্ড): \"যদি জীবনের কোনো মুহূর্তে একা লেগে থাকে, তবে এই গানটি শুধু আপনার জন্য...\"",
                "💔 হুক ২ (০-৫ সেকেন্ড): \"এই সুরটি শুনলে ফেলে আসা চেনা মানুষটার কথা মনে পড়তে বাধ্য!\"",
                "✨ হুক ৩ (০-৫ সেকেন্ড): \"২০২৬ সালের সবচেয়ে হৃদয়ছোঁয়া বাংলা গান! পুরো গান শুনতে লিংকে ক্লিক করুন।\""
            ),
            whatsappStatusText = """🎵 নতুন গান রিলিজ হলো: "$title" - $cleanArtist
যে সুর এক মুহূর্তেই মন ভালো করে দেয় অথবা স্মৃতির জানালায় নিয়ে যায়...
ইউটিউবে পুরো গানটি এখনই শুনুন: [YouTube Link]""",
            callToAction = "ভিডিওটি ভালো লাগলে লাইক দিন, কমেন্টে আপনার প্রিয় লিরিক্সটি লিখে জানান এবং আপনার বন্ধুদের সাথে শেয়ার করুন!"
        )

        return GeneratedStrategy(
            songTitle = title,
            artistName = artist,
            mood = mood,
            genre = genre,
            language = input.language,
            lyricsSnippet = input.lyrics.take(150),
            titles = titles,
            fullDescription = description,
            tags = tagsList,
            tagsCommaSeparated = tagsComma,
            thumbnailConcepts = thumbnailConcepts,
            socialTeasers = socialTeasers
        )
    }
}
