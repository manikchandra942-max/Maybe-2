package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.GeneratedStrategy
import com.example.data.model.SampleSongPresets
import com.example.data.model.SongInput
import com.example.data.repository.StrategyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTab(val labelBn: String, val labelEn: String) {
    INPUT("ইনপুট ও গান", "Song Input"),
    STRATEGY("এসইও রেজাল্ট", "Strategy Result"),
    SAVED("সংরক্ষিত গান", "Saved Songs"),
    GUIDE("মার্কেটিং গাইড", "SEO Guide")
}

class MusicLaunchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StrategyRepository by lazy {
        val db = AppDatabase.getDatabase(application)
        StrategyRepository(db.strategyDao())
    }

    private val _songInput = MutableStateFlow(SampleSongPresets.presets.first())
    val songInput: StateFlow<SongInput> = _songInput.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val _currentStrategy = MutableStateFlow<GeneratedStrategy?>(null)
    val currentStrategy: StateFlow<GeneratedStrategy?> = _currentStrategy.asStateFlow()

    private val _selectedTab = MutableStateFlow(AppTab.INPUT)
    val selectedTab: StateFlow<AppTab> = _selectedTab.asStateFlow()

    private val _selectedStrategySection = MutableStateFlow(0)
    val selectedStrategySection: StateFlow<Int> = _selectedStrategySection.asStateFlow()

    private val _customApiKey = MutableStateFlow("")
    val customApiKey: StateFlow<String> = _customApiKey.asStateFlow()

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    val savedStrategies: StateFlow<List<GeneratedStrategy>> = repository.savedStrategies
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateTitle(newTitle: String) {
        _songInput.value = _songInput.value.copy(title = newTitle)
    }

    fun updateLyrics(newLyrics: String) {
        _songInput.value = _songInput.value.copy(lyrics = newLyrics)
    }

    fun updateMood(newMood: String) {
        _songInput.value = _songInput.value.copy(mood = newMood)
    }

    fun updateGenre(newGenre: String) {
        _songInput.value = _songInput.value.copy(genre = newGenre)
    }

    fun updateArtist(newArtist: String) {
        _songInput.value = _songInput.value.copy(artist = newArtist)
    }

    fun updateComposer(newComposer: String) {
        _songInput.value = _songInput.value.copy(composer = newComposer)
    }

    fun updateLyricist(newLyricist: String) {
        _songInput.value = _songInput.value.copy(lyricist = newLyricist)
    }

    fun updateChannelName(newChannel: String) {
        _songInput.value = _songInput.value.copy(channelName = newChannel)
    }

    fun updateLanguage(newLanguage: String) {
        _songInput.value = _songInput.value.copy(language = newLanguage)
    }

    fun updateReleaseType(newReleaseType: String) {
        _songInput.value = _songInput.value.copy(releaseType = newReleaseType)
    }

    fun loadPreset(preset: SongInput) {
        _songInput.value = preset
        viewModelScope.launch {
            _snackbarMessage.emit("\"${preset.title}\" গানের ডেমো লোড হয়েছে!")
        }
    }

    fun clearInput() {
        _songInput.value = SongInput()
    }

    fun setTab(tab: AppTab) {
        _selectedTab.value = tab
    }

    fun setStrategySection(sectionIndex: Int) {
        _selectedStrategySection.value = sectionIndex
    }

    fun updateCustomApiKey(key: String) {
        _customApiKey.value = key
    }

    fun generateStrategy() {
        if (_songInput.value.title.isBlank() && _songInput.value.lyrics.isBlank()) {
            viewModelScope.launch {
                _snackbarMessage.emit("অনুগ্রহ করে অন্তত গানের নাম অথবা লিরিক্স দিন!")
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            try {
                val result = repository.generateStrategy(_songInput.value, _customApiKey.value)
                _currentStrategy.value = result
                _selectedStrategySection.value = 0
                _selectedTab.value = AppTab.STRATEGY
                _snackbarMessage.emit("✨ ৫টি মার্কেটিং প্ল্যান তৈরি সম্পন্ন!")
            } catch (e: Exception) {
                _snackbarMessage.emit("ত্রুটি: ${e.message}")
            } finally {
                _isGenerating.value = false
            }
        }
    }

    fun saveCurrentStrategy() {
        val strategy = _currentStrategy.value ?: return
        viewModelScope.launch {
            try {
                repository.saveStrategy(strategy)
                _snackbarMessage.emit("✅ স্ট্র্যাটেজি সফলভাবে সংরক্ষণ করা হয়েছে!")
            } catch (e: Exception) {
                _snackbarMessage.emit("সংরক্ষণ ব্যর্থ: ${e.message}")
            }
        }
    }

    fun deleteSavedStrategy(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteStrategy(id)
                _snackbarMessage.emit("🗑️ তালিকা থেকে মুছে ফেলা হয়েছে")
            } catch (e: Exception) {
                _snackbarMessage.emit("মুছে ফেলা ব্যর্থ: ${e.message}")
            }
        }
    }

    fun selectSavedStrategy(strategy: GeneratedStrategy) {
        _currentStrategy.value = strategy
        _selectedStrategySection.value = 0
        _selectedTab.value = AppTab.STRATEGY
    }

    fun emitSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarMessage.emit(message)
        }
    }
}
