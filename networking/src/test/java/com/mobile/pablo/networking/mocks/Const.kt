package com.mobile.pablo.networking.mocks

import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.networking.model.common.IdResponse
import com.mobile.pablo.networking.model.common.PageInfoResponse
import com.mobile.pablo.networking.model.common.ThumbnailResponse
import com.mobile.pablo.networking.model.common.ThumbnailsResponse
import com.mobile.pablo.networking.model.popular.PopularItemResponse
import com.mobile.pablo.networking.model.popular.PopularResponse
import com.mobile.pablo.networking.model.popular.PopularSnippetResponse
import com.mobile.pablo.networking.model.search.SearchItemResponse
import com.mobile.pablo.networking.model.search.SearchResponse
import com.mobile.pablo.networking.model.search.SearchSnippetResponse

internal val MOCK_DOG_SEARCH = SearchResponse(
    kind = "youtube#searchListResponse",
    etag = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
    nextPageToken = "CAUQAA",
    regionCode = "PL",
    pageInfo = PageInfoResponse(
        totalResults = 1000000,
        resultsPerPage = 5
    ),
    items = listOf(
        SearchItemResponse(
            kind = "youtube#searchResult",
            etag = "svU9zuPl1r0hypYbIXMTjnUJGfQ",
            id = IdResponse(
                kind = "youtube#video",
                videoId = "vOThRCun5GY"
            ),
            snippet = SearchSnippetResponse(
                publishedAt = "2023-06-19T12:45:48Z",
                channelId = "UCMp-0bU-PA7BNNR-zIvEydA",
                title = "I found a dog outside my gym \uD83D\uDE33",
                description = EMPTY_STRING,
                thumbnails = ThumbnailsResponse(
                    default = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailResponse(
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
        SearchItemResponse(
            kind = "youtube#searchResult",
            etag = "elhoJXuEkd1u5KN1JMxXeotpjic",
            id = IdResponse(
                kind = "youtube#video",
                videoId = "SbTheNHE0VA"
            ),
            snippet = SearchSnippetResponse(
                publishedAt = "2023-10-07T11:28:49Z",
                channelId = "UCweWFpJ5CiJimQJAAx4aSew",
                title = "Did the 3rd dog eat cat nip? #dogs #dogsports #flyball",
                description = EMPTY_STRING,
                thumbnails = ThumbnailsResponse(
                    default = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailResponse(
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

internal val MOCK_POPULAR_ITEM = PopularResponse(
    kind = "youtube#videoListResponse",
    etag = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
    nextPageToken = "CAUQAA",
    prevPageToken = "KUQASS",
    pageInfo = PageInfoResponse(
        totalResults = 1000000,
        resultsPerPage = 5
    ),

    items = listOf(
        PopularItemResponse(
            kind = "youtube#searchResult",
            etag = "svU9zuPl1r0hypYbIXMTjnUJGfQ",
            id = "vOThRCun5GY",
            snippet = PopularSnippetResponse(
                publishedAt = "2023-06-19T12:45:48Z",
                channelId = "UCMp-0bU-PA7BNNR-zIvEydA",
                title = "I found a dog outside my gym \uD83D\uDE33",
                description = EMPTY_STRING,
                thumbnails = ThumbnailsResponse(
                    default = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/hqdefault.jpg",
                        width = 480,
                        height = 360
                    )
                ),
                channelTitle = "Noel Deyzel",
                liveBroadcastContent = "none"
            )
        ),
        PopularItemResponse(
            kind = "youtube#searchResult",
            etag = "elhoJXuEkd1u5KN1JMxXeotpjic",
            id = "SbTheNHE0VA",
            snippet = PopularSnippetResponse(
                publishedAt = "2023-10-07T11:28:49Z",
                channelId = "UCweWFpJ5CiJimQJAAx4aSew",
                title = "Did the 3rd dog eat cat nip? #dogs #dogsports #flyball",
                description = EMPTY_STRING,
                thumbnails = ThumbnailsResponse(
                    default = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailResponse(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailResponse(
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