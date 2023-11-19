package com.mobile.pablo.domain.mock.const

import com.mobile.pablo.core.model.common.IdDTO
import com.mobile.pablo.core.model.common.PageInfoDTO
import com.mobile.pablo.core.model.common.ThumbnailDTO
import com.mobile.pablo.core.model.common.ThumbnailsDTO
import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.core.model.popular.PopularSnippetDTO
import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.core.model.search.SearchItemDTO
import com.mobile.pablo.core.model.search.SearchSnippetDTO

internal val MOCK_DOG_SEARCH = SearchDTO(
    kind = "youtube#searchListResponse",
    etag = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
    nextPageToken = "CAUQAA",
    regionCode = "PL",
    pageInfo = PageInfoDTO(
        totalResults = 1000000,
        resultsPerPage = 5
    ),
    items = listOf(
        SearchItemDTO(
            kind = "youtube#searchResult",
            etag = "svU9zuPl1r0hypYbIXMTjnUJGfQ",
            id = IdDTO(
                kind = "youtube#video",
                videoId = "vOThRCun5GY"
            ),
            snippet = SearchSnippetDTO(
                publishedAt = "2023-06-19T12:45:48Z",
                channelId = "UCMp-0bU-PA7BNNR-zIvEydA",
                title = "I found a dog outside my gym \uD83D\uDE33",
                description = "",
                thumbnails = ThumbnailsDTO(
                    default = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/hqdefault.jpg",
                        width = 480,
                        height = 360
                    )
                ),
                channelTitle = "Noel Deyzel",
                liveBroadcastContent = "none",
                publishTime = "2023-06-19T12:45:48Z"
            )
        ),
        SearchItemDTO(
            kind = "youtube#searchResult",
            etag = "elhoJXuEkd1u5KN1JMxXeotpjic",
            id = IdDTO(
                kind = "youtube#video",
                videoId = "SbTheNHE0VA"
            ),
            snippet = SearchSnippetDTO(
                publishedAt = "2023-10-07T11:28:49Z",
                channelId = "UCweWFpJ5CiJimQJAAx4aSew",
                title = "Did the 3rd dog eat cat nip? #dogs #dogsports #flyball",
                description = "",
                thumbnails = ThumbnailsDTO(
                    default = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/hqdefault.jpg",
                        width = 480,
                        height = 360
                    )
                ),
                channelTitle = "RunDownRiver",
                liveBroadcastContent = "none",
                publishTime = "2023-06-19T12:45:48Z"
            )
        )
    )
)

internal val MOCK_POPULAR_ITEM = PopularDTO(
    kind = "youtube#videoListResponse",
    etag = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
    nextPageToken = "CAUQAA",
    prevPageToken = "KUQASS",
    pageInfo = PageInfoDTO(
        totalResults = 1000000,
        resultsPerPage = 5
    ),

    items = listOf(
        PopularItemDTO(
            kind = "youtube#searchResult",
            etag = "svU9zuPl1r0hypYbIXMTjnUJGfQ",
            id = "vOThRCun5GY",
            snippet = PopularSnippetDTO(
                publishedAt = "2023-06-19T12:45:48Z",
                channelId = "UCMp-0bU-PA7BNNR-zIvEydA",
                title = "I found a dog outside my gym \uD83D\uDE33",
                description = "",
                thumbnails = ThumbnailsDTO(
                    default = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/hqdefault.jpg",
                        width = 480,
                        height = 360
                    )
                ),
                channelTitle = "Noel Deyzel",
                liveBroadcastContent = "none"
            )
        ),
        PopularItemDTO(
            kind = "youtube#searchResult",
            etag = "elhoJXuEkd1u5KN1JMxXeotpjic",
            id = "SbTheNHE0VA",
            snippet = PopularSnippetDTO(
                publishedAt = "2023-10-07T11:28:49Z",
                channelId = "UCweWFpJ5CiJimQJAAx4aSew",
                title = "Did the 3rd dog eat cat nip? #dogs #dogsports #flyball",
                description = "",
                thumbnails = ThumbnailsDTO(
                    default = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailDTO(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/hqdefault.jpg",
                        width = 480,
                        height = 360
                    )
                ),
                channelTitle = "RunDownRiver",
                liveBroadcastContent = "none"
            )
        )
    )
)