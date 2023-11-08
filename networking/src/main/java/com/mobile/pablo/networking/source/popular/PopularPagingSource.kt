package com.mobile.pablo.networking.source.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.core.util.EMPTY_STRING
import javax.inject.Inject

class PopularPagingSource @Inject constructor(
    private val popularDataSource: PopularDataSource
) : PagingSource<String, PopularItemDTO>() {

    private var nextPageToken = EMPTY_STRING
    private var prevPageToken = EMPTY_STRING

    override fun getRefreshKey(state: PagingState<String, PopularItemDTO>): String = nextPageToken

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularItemDTO> {
        val popularResponse = popularDataSource.getPopularVideos(
            regionCode = "US",
            nextPageToken
        )

        return when {
            popularResponse.isSuccessful -> {
                nextPageToken = popularResponse.data!!.nextPageToken!!
                prevPageToken = popularResponse.data!!.prevPageToken ?: EMPTY_STRING

                LoadResult.Page(
                    data = popularResponse.data!!.items!!.filterNotNull(),
                    prevKey = prevPageToken,
                    nextKey = nextPageToken
                )
            }

            else ->
                LoadResult.Error(popularResponse.error!!)
        }
    }
}