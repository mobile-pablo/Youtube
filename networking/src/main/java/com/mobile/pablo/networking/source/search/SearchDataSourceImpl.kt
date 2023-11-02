package com.mobile.pablo.networking.source.search

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.networking.mapper.search.SearchResponseMapper
import com.mobile.pablo.networking.service.YoutubeService
import javax.inject.Inject

internal class SearchDataSourceImpl @Inject constructor(
    private val youtubeService: YoutubeService,
    private val searchResponseMapper: SearchResponseMapper
) : SearchDataSource {

    override suspend fun getSearchVideos(query: String): DataTransfer<SearchDTO> {
        val searchResponse = youtubeService.getSearchVideos(q = query)

        return when {
            searchResponse.isSuccessful -> {
                val searchResult = searchResponse.body()
                DataTransfer(searchResponseMapper.map(searchResult))
            }

            else -> {
                DataTransfer(error = Exception(searchResponse.message()))
            }
        }
    }
}