package com.mobile.pablo.domain.mapper.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.domain.mapper.common.ContentDetailsMapper
import com.mobile.pablo.domain.mapper.common.StatisticsMapper
import com.mobile.pablo.domain.model.popular.PopularItem
import javax.inject.Inject

class PopularItemMapper @Inject constructor(
    private val popularSnippetMapper: PopularSnippetMapper,
    private val contentDetailsMapper: ContentDetailsMapper,
    private val statisticsMapper: StatisticsMapper
) {

    fun map(dto: PopularItemDTO?): PopularItem? {
        return dto?.run {
            PopularItem(
                kind = kind,
                etag = etag,
                id = id,
                snippet = popularSnippetMapper.map(snippet),
                contentDetails = contentDetailsMapper.map(contentDetails),
                statistics = statisticsMapper.map(statistics)
            )
        }
    }
}
