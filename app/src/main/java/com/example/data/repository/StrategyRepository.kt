package com.example.data.repository

import com.example.data.api.GeminiAiStrategyService
import com.example.data.local.StrategyDao
import com.example.data.local.StrategyJsonMapper
import com.example.data.model.GeneratedStrategy
import com.example.data.model.SongInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StrategyRepository(
    private val dao: StrategyDao,
    private val geminiService: GeminiAiStrategyService = GeminiAiStrategyService()
) {

    val savedStrategies: Flow<List<GeneratedStrategy>> = dao.getAllStrategies().map { entities ->
        entities.map { StrategyJsonMapper.toDomain(it) }
    }

    suspend fun generateStrategy(input: SongInput, customApiKey: String? = null): GeneratedStrategy {
        return geminiService.generateStrategy(input, customApiKey)
    }

    suspend fun saveStrategy(strategy: GeneratedStrategy): Long {
        val entity = StrategyJsonMapper.toEntity(strategy)
        return dao.insertStrategy(entity)
    }

    suspend fun deleteStrategy(id: Long) {
        dao.deleteById(id)
    }
}
