package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.engine.OfflineMusicStrategyEngine
import com.example.data.local.StrategyJsonMapper
import com.example.data.model.SampleSongPresets
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("MusicLaunch", appName)
  }

  @Test
  fun `test music strategy engine generates all 5 points`() {
    val sample = SampleSongPresets.presets.first()
    val strategy = OfflineMusicStrategyEngine.generateStrategy(sample)

    // 1. Five catchy and SEO-friendly YouTube titles
    assertEquals(5, strategy.titles.size)
    strategy.titles.forEach { titleItem ->
      assertTrue(titleItem.title.isNotBlank())
      assertTrue(titleItem.angle.isNotBlank())
    }

    // 2. Detailed and informative description with keywords and hashtags
    assertTrue(strategy.fullDescription.contains("কণ্ঠ (Singer): ${sample.artist}"))
    assertTrue(strategy.fullDescription.contains("সম্পূর্ণ লিরিক্স"))
    assertTrue(strategy.fullDescription.contains("#${sample.title.replace(" ", "")}"))

    // 3. YouTube Tags
    assertTrue(strategy.tags.isNotEmpty())
    assertTrue(strategy.tagsCommaSeparated.contains(sample.artist))

    // 4. Thumbnail Concepts
    assertEquals(3, strategy.thumbnailConcepts.size)
    strategy.thumbnailConcepts.forEach { concept ->
      assertTrue(concept.conceptTitle.isNotBlank())
      assertTrue(concept.textOverlayText.isNotBlank())
    }

    // 5. Social Teasers
    assertTrue(strategy.socialTeasers.reelsShortsCaption.isNotBlank())
    assertTrue(strategy.socialTeasers.viralAudioHooks.isNotEmpty())
    assertTrue(strategy.socialTeasers.whatsappStatusText.isNotBlank())
  }

  @Test
  fun `test strategy mapper to entity and back`() {
    val sample = SampleSongPresets.presets.first()
    val strategy = OfflineMusicStrategyEngine.generateStrategy(sample)
    val entity = StrategyJsonMapper.toEntity(strategy)
    val domain = StrategyJsonMapper.toDomain(entity)

    assertEquals(strategy.songTitle, domain.songTitle)
    assertEquals(strategy.artistName, domain.artistName)
    assertEquals(strategy.titles.size, domain.titles.size)
    assertEquals(strategy.thumbnailConcepts.size, domain.thumbnailConcepts.size)
    assertEquals(strategy.socialTeasers.viralAudioHooks.size, domain.socialTeasers.viralAudioHooks.size)
  }
}
