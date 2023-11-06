package com.mobile.pablo.networking.source.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.storage.sharedprefs.Setting
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import javax.inject.Inject

class PopularPagingSource @Inject constructor(
    private val popularDataSource: PopularDataSource,
    private val sharedPreferencesManager: SharedPreferencesManager
) : PagingSource<String, DataTransfer<PopularDTO>>() {

    override fun getRefreshKey(state: PagingState<String, DataTransfer<PopularDTO>>): String = EMPTY_STRING

    override suspend fun load(params: LoadParams<String>): LoadResult<String, DataTransfer<PopularDTO>> {
        val nextPageToken = sharedPreferencesManager.getString(Setting.NEXT_PAGE_TOKEN) ?: EMPTY_STRING
        val popularResponse = popularDataSource.getPopularVideos(
            regionCode = "US",
            nextPageToken
        )

        return when {
            popularResponse.isSuccessful -> {
                sharedPreferencesManager.setString(Setting.NEXT_PAGE_TOKEN, popularResponse.data!!.nextPageToken ?: EMPTY_STRING)
                sharedPreferencesManager.setString(Setting.PREV_PAGE_TOKEN, popularResponse.data!!.prevPageToken ?: EMPTY_STRING)
                LoadResult.Page(
                    data = listOf(DataTransfer(data = popularResponse.data!!)),
                    prevKey = popularResponse.data!!.prevPageToken,
                    nextKey = popularResponse.data!!.nextPageToken
                )
            }

            else ->
                LoadResult.Error(popularResponse.error!!)
        }
    }
}