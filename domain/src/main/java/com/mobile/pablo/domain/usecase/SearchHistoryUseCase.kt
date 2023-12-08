package com.mobile.pablo.domain.usecase

import com.mobile.pablo.storage.source.searchHistory.SearchHistoryDataStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

sealed class SearchHistoryUseCase {

    class GetSearchHistory
    @Inject
    constructor(
        private val searchHistoryDataStorage: SearchHistoryDataStorage
    ) : VideosUseCase() {
        operator fun invoke(): Flow<List<String>> = searchHistoryDataStorage.getSearchHistory()
    }

    class UpsertSearchHistoryItem
    @Inject
    constructor(
        private val searchHistoryDataStorage: SearchHistoryDataStorage
    ) : VideosUseCase() {
        suspend operator fun invoke(query: String) = searchHistoryDataStorage.upsertSearchHistoryItem(query)
    }
}
