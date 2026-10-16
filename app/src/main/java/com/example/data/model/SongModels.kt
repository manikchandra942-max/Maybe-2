package com.example.data.model

data class SongInput(
    val title: String = "",
    val lyrics: String = "",
    val mood: String = "রোমান্টিক / Romantic",
    val genre: String = "আধুনিক বাংলা গান / Modern Pop",
    val artist: String = "",
    val composer: String = "",
    val lyricist: String = "",
    val channelName: String = "",
    val language: String = "বাংলা (Bengali)",
    val releaseType: String = "অফিসিয়াল মিউজিক ভিডিও (Official Music Video)",
    val targetAudience: String = "তরুণ ও যুবসমাজ / Youth & Emotion"
)

data class TitleItem(
    val title: String,
    val angle: String, // e.g. "আবেগঘন হুক", "সার্চ এসইও ফোকাসড", "ভাইরাল ক্লিক-ম্যাগনেট", "অফিসিয়াল ফরম্যাট", "লিরিক্যাল পয়েটিক"
    val characterCount: Int = title.length,
    val scoreRating: String = "৯৮%",
    val whyItWorks: String = ""
)

data class ThumbnailConcept(
    val conceptTitle: String,
    val visualDescription: String,
    val colorPalette: String,
    val textOverlayText: String,
    val facialExpression: String,
    val lightingStyle: String
)

data class SocialTeaserData(
    val reelsShortsCaption: String,
    val viralAudioHooks: List<String>,
    val whatsappStatusText: String,
    val callToAction: String
)

data class GeneratedStrategy(
    val id: Long = 0,
    val songTitle: String,
    val artistName: String,
    val mood: String,
    val genre: String,
    val language: String,
    val lyricsSnippet: String,
    val titles: List<TitleItem>,
    val fullDescription: String,
    val tags: List<String>,
    val tagsCommaSeparated: String,
    val thumbnailConcepts: List<ThumbnailConcept>,
    val socialTeasers: SocialTeaserData,
    val createdAt: Long = System.currentTimeMillis()
)

object SampleSongPresets {
    val presets = listOf(
        SongInput(
            title = "তুমি হীনা",
            lyrics = """কখনো ভাবিনি এভাবে হারাবো তোমায়
স্মৃতির পাতায় জমেছে নীরব কান্না
বৃষ্টির রাতে একলা ঘরে আজও
তোমার চেনা সুবাস ফিরে ফিরে আসে।

যদি কোনোদিন মনে পড়ে আমায়
খুঁজে নিও ওই নীল সীমানায়
আমি তো তোমারি ছিলাম চিরকাল
তুমি হীনা এ জীবন যেন আধাঁর রাত।""",
            mood = "বিরহ ও কষ্ট / Heartbreak & Melancholy",
            genre = "স্যাড মেলোডি / Sad Ballad",
            artist = "তানভীর শুভ",
            composer = "অয়ন চক্রবর্তী",
            lyricist = "ফারহান ইসলাম",
            channelName = "SoundPulse Music",
            language = "বাংলা (Bengali)",
            releaseType = "অফিসিয়াল মিউজিক ভিডিও (Official Music Video)",
            targetAudience = "হৃদয়ভাঙা রোমান্টিক শ্রোতা"
        ),
        SongInput(
            title = "মাটির মেঠোপথ",
            lyrics = """ও মন রে চল যাই দূর গাঁয়ে
যেথা বাজে বাঁশি ভাটির সুরে
ধানের শীষে দোলে শিশির কণা
মাটির গন্ধে জুড়ায় পরাণ।

শহরের এই ইট পাথরের ভিড়ে
মন যে আমার হারিয়ে ফেলে সুর
আয় রে ফিরে শ্যামল ছায়ায়
নদীর কূলে একতারা বাজায়।""",
            mood = "উৎসবমুখর ও আন্তরিক / Earthy Folk Fusion",
            genre = "ফোক ফিউশন / Bangla Folk Fusion",
            artist = "বাউল সুজন ও রিদম ব্যান্ড",
            composer = "রাকিব হাসান",
            lyricist = "পল্লীগীতিকার রশীদ",
            channelName = "Matir Shur Records",
            language = "বাংলা (Bengali)",
            releaseType = "লাইভ স্টুডিও সেশন (Studio Session)",
            targetAudience = "ফোক ও ফিউশন মিউজিকপ্রেমী"
        ),
        SongInput(
            title = "রাতের শহর",
            lyrics = """নিয়ন বাতির আলোয় একা ড্রাইভ
স্পিকারে বাজছে লো-ফাই ভাইব
শহরের রাস্তায় হাজারো স্মৃতি
তুমি আর আমি নেই কোনো রীতি।

অপেক্ষার প্রহর গুনে ক্লান্ত রাত
বাতাসে ওড়ে তোমার ফেলে যাওয়া কথা
আজও চেনা সিগন্যালে চোখ রেখে যাই
তোমাকে ছাড়া এই শহর বড় অপরিচিত।""",
            mood = "নস্টালজিক ও স্টাইলিশ / Chill & Nostalgic",
            genre = "আরবান পপ / Lo-Fi Urban Pop",
            artist = "আরিফ জাওয়াদ ft. নিলা",
            composer = "বিটমাস্টার জনি",
            lyricist = "আরিফ জাওয়াদ",
            channelName = "Midnight Groove",
            language = "বাংলা + ইংরেজি (Bilingual)",
            releaseType = "অফিসিয়াল লিরিক্যাল ভিডিও (Lyrical Video)",
            targetAudience = "জেন-জি ও তরুণ ড্রাইভ মিউজিক ফ্যান"
        )
    )
}
