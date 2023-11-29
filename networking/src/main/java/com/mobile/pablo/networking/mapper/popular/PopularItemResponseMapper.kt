package com.mobile.pablo.networking.mapper.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.networking.mapper.common.ContentDetailsResponseMapper
import com.mobile.pablo.networking.mapper.common.StatisticsResponseMapper
import com.mobile.pablo.networking.model.popular.PopularItemResponse
import javax.inject.Inject

internal class PopularItemResponseMapper
    @Inject
    constructor(
        private val popularSnippetResponseMapper: PopularSnippetResponseMapper,
        private val contentDetailsResponseMapper: ContentDetailsResponseMapper,
        private val statisticsResponseMapper: StatisticsResponseMapper
    ) {
        fun map(response: PopularItemResponse?): PopularItemDTO? {
            return response?.run {
                PopularItemDTO(
                    kind = kind,
                    etag = etag,
                    id = id,
                    snippet = popularSnippetResponseMapper.map(snippet),
                    contentDetails = contentDetailsResponseMapper.map(contentDetails),
                    statistics = statisticsResponseMapper.map(statistics)
                )
            }
        }
    }
