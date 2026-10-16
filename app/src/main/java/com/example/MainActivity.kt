package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.AudioEqualizerVisualizer
import com.example.ui.screens.InputScreen
import com.example.ui.screens.SavedHistoryScreen
import com.example.ui.screens.SeoGuideScreen
import com.example.ui.screens.StrategyResultScreen
import com.example.ui.theme.CyanGlow
import com.example.ui.theme.EmeraldSEO
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.YouTubeRed
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.MusicLaunchViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MusicLaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(darkTheme = true) {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(viewModel: MusicLaunchViewModel) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val songInput by viewModel.songInput.collectAsStateWithLifecycle()
    val isGenerating by viewModel.isGenerating.collectAsStateWithLifecycle()
    val currentStrategy by viewModel.currentStrategy.collectAsStateWithLifecycle()
    val savedStrategies by viewModel.savedStrategies.collectAsStateWithLifecycle()
    val selectedSectionIndex by viewModel.selectedStrategySection.collectAsStateWithLifecycle()
    val customApiKey by viewModel.customApiKey.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    // Listen to snackbar events
    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    // Back handling
    BackHandler(enabled = selectedTab != AppTab.INPUT) {
        viewModel.setTab(AppTab.INPUT)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = StudioBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBarContent(
                selectedTab = selectedTab,
                onBack = { viewModel.setTab(AppTab.INPUT) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = StudioSurface,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("bottom_nav_bar")
            ) {
                NavigationBarItem(
                    selected = selectedTab == AppTab.INPUT,
                    onClick = { viewModel.setTab(AppTab.INPUT) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = "Song Input",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("ইনপুট", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = NeonPurple,
                        selectedTextColor = NeonPurple,
                        indicatorColor = NeonPurple.copy(alpha = 0.15f),
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted
                    ),
                    modifier = Modifier.testTag("tab_input")
                )

                NavigationBarItem(
                    selected = selectedTab == AppTab.STRATEGY,
                    onClick = { viewModel.setTab(AppTab.STRATEGY) },
                    icon = {
                        BadgedBox(badge = {
                            if (currentStrategy != null) {
                                Badge(containerColor = YouTubeRed) { Text("5") }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Strategy Result",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("স্ট্র্যাটেজি", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = YouTubeRed,
                        selectedTextColor = YouTubeRed,
                        indicatorColor = YouTubeRed.copy(alpha = 0.15f),
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted
                    ),
                    modifier = Modifier.testTag("tab_strategy")
                )

                NavigationBarItem(
                    selected = selectedTab == AppTab.SAVED,
                    onClick = { viewModel.setTab(AppTab.SAVED) },
                    icon = {
                        BadgedBox(badge = {
                            if (savedStrategies.isNotEmpty()) {
                                Badge(containerColor = CyanGlow) { Text("${savedStrategies.size}") }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Saved Strategies",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("সংরক্ষিত", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CyanGlow,
                        selectedTextColor = CyanGlow,
                        indicatorColor = CyanGlow.copy(alpha = 0.15f),
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted
                    ),
                    modifier = Modifier.testTag("tab_saved")
                )

                NavigationBarItem(
                    selected = selectedTab == AppTab.GUIDE,
                    onClick = { viewModel.setTab(AppTab.GUIDE) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "SEO Guide",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("গাইড", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = EmeraldSEO,
                        selectedTextColor = EmeraldSEO,
                        indicatorColor = EmeraldSEO.copy(alpha = 0.15f),
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted
                    ),
                    modifier = Modifier.testTag("tab_guide")
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 80.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                AppTab.INPUT -> {
                    InputScreen(
                        songInput = songInput,
                        isGenerating = isGenerating,
                        customApiKey = customApiKey,
                        onTitleChange = viewModel::updateTitle,
                        onLyricsChange = viewModel::updateLyrics,
                        onMoodChange = viewModel::updateMood,
                        onGenreChange = viewModel::updateGenre,
                        onArtistChange = viewModel::updateArtist,
                        onComposerChange = viewModel::updateComposer,
                        onLyricistChange = viewModel::updateLyricist,
                        onChannelChange = viewModel::updateChannelName,
                        onReleaseTypeChange = viewModel::updateReleaseType,
                        onLoadPreset = viewModel::loadPreset,
                        onClearInput = viewModel::clearInput,
                        onApiKeyChange = viewModel::updateCustomApiKey,
                        onGenerate = viewModel::generateStrategy
                    )
                }

                AppTab.STRATEGY -> {
                    StrategyResultScreen(
                        strategy = currentStrategy,
                        selectedSectionIndex = selectedSectionIndex,
                        onSectionChange = viewModel::setStrategySection,
                        onSaveStrategy = viewModel::saveCurrentStrategy,
                        onBackToInput = { viewModel.setTab(AppTab.INPUT) },
                        onShowMessage = viewModel::emitSnackbar
                    )
                }

                AppTab.SAVED -> {
                    SavedHistoryScreen(
                        savedList = savedStrategies,
                        onSelectStrategy = viewModel::selectSavedStrategy,
                        onDeleteStrategy = viewModel::deleteSavedStrategy
                    )
                }

                AppTab.GUIDE -> {
                    SeoGuideScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarContent(
    selectedTab: AppTab,
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = StudioSurface
        ),
        navigationIcon = {
            if (selectedTab != AppTab.INPUT) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(34.dp)
                        .background(
                            Brush.linearGradient(listOf(YouTubeRed, NeonPurple)),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "MusicLaunch",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = TextPrimary,
                            letterSpacing = 0.5.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        color = YouTubeRed,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "SEO",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            ),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                        )
                    }
                }
                Text(
                    text = "ইউটিউব স্ট্র্যাটেজিস্ট ও মিউজিক মার্কেটিং এক্সপার্ট",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                )
            }
        },
        actions = {
            AudioEqualizerVisualizer(
                modifier = Modifier.padding(end = 16.dp),
                barCount = 6
            )
        }
    )
}
