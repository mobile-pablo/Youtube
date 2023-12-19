package com.mobile.pablo.networking.source.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobile.pablo.core.model.search.SearchItemDTO
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val query: String
) : PagingSource<String, SearchItemDTO>() {

    private var nextPageToken: String?
    private var prevPageToken: String?

    init {
        nextPageToken = null
        prevPageToken = null
    }

    override fun getRefreshKey(state: PagingState<String, SearchItemDTO>): String? = nextPageToken

    override suspend fun load(params: LoadParams<String>): LoadResult<String, SearchItemDTO> {
        val searchResponse =
            searchDataSource.getSearchVideos(query, nextPageToken)

        searchResponse.data.let { search ->
            return when (search) {
                null -> LoadResult.Error(searchResponse.error!!)

                else -> {
                    nextPageToken = search.nextPageToken
                    prevPageToken = search.prevPageToken

                    LoadResult.Page(
                        data = search.items!!.filterNotNull(),
                        prevKey = prevPageToken,
                        nextKey = nextPageToken
                    )
                }
            }
        }
    }
}
