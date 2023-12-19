package com.mobile.pablo.networking.source.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.pablo.core.model.popular.PopularItemDTO
import javax.inject.Inject

class PopularPagingSource @Inject constructor(
    private val popularDataSource: PopularDataSource
) : PagingSource<String, PopularItemDTO>() {

    private var nextPageToken: String?
    private var prevPageToken: String?

    init {
        nextPageToken = null
        prevPageToken = null
    }

    override fun getRefreshKey(state: PagingState<String, PopularItemDTO>): String? = nextPageToken

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularItemDTO> {
        val popularResponse =
            popularDataSource.getPopularVideos(
                regionCode = "US",
                nextPageToken
            )

        popularResponse.data.let { popular ->
            return when (popular) {
                null -> LoadResult.Error(popularResponse.error!!)

                else -> {
                    nextPageToken = popular.nextPageToken
                    prevPageToken = popular.prevPageToken

                    LoadResult.Page(
                        data = popular.items!!.filterNotNull(),
                        prevKey = prevPageToken,
                        nextKey = nextPageToken
                    )
                }
            }
        }
    }
}
