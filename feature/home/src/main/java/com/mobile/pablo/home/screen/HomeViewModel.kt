package com.mobile.pablo.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.domain.usecase.VideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPopularVideosUseCase: VideosUseCase.GetPopularVideos
) : ViewModel() {

    companion object {

        private const val POPULAR_VIDEO_DEBOUNCE_MILLIS = 1000L
    }

    @OptIn(FlowPreview::class)
    val popularState: Flow<PagingData<PopularItem>> =
        getPopularVideosUseCase()
            .map { pagingData ->
                pagingData.filter { item ->
                    when {
                        item.id == null -> false
                        item.snippet!!.thumbnails!!.medium!!.url.isNullOrBlank() -> false
                        else -> true
                    }
                }
            }
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .debounce(POPULAR_VIDEO_DEBOUNCE_MILLIS)
}
