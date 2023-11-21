package com.mobile.pablo.storage.mocks

import com.mobile.pablo.storage.database.entity.common.IdEntity
import com.mobile.pablo.storage.database.entity.common.PageInfoEntity
import com.mobile.pablo.storage.database.entity.common.ThumbnailEntity
import com.mobile.pablo.storage.database.entity.common.ThumbnailsEntity
import com.mobile.pablo.storage.database.entity.search.SearchEntity
import com.mobile.pablo.storage.database.entity.search.SearchItemEntity
import com.mobile.pablo.storage.database.entity.search.SearchSnippetEntity
import com.mobile.pablo.storage.database.entity.search.SearchWithItemEntity

internal val MOCK_DOG_SEARCH = SearchWithItemEntity(
    search = SearchEntity(
        kind = "youtube#searchListResponse",
        etag = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
        nextPageToken = "CAUQAA",
        regionCode = "PL",
        pageInfo = PageInfoEntity(
            totalResults = 1000000,
            resultsPerPage = 5
        )
    ),
    items = listOf(
        SearchItemEntity(
            parentId = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
            kind = "youtube#searchResult",
            etag = "svU9zuPl1r0hypYbIXMTjnUJGfQ",
            id = IdEntity(
                kind = "youtube#video",
                videoId = "vOThRCun5GY"
            ),
            snippet = SearchSnippetEntity(
                publishedAt = "2023-06-19T12:45:48Z",
                channelId = "UCMp-0bU-PA7BNNR-zIvEydA",
                title = "I found a dog outside my gym \uD83D\uDE33",
                description = "",
                thumbnails = ThumbnailsEntity(
                    default = ThumbnailEntity(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailEntity(
                        url = "https://i.ytimg.com/vi/vOThRCun5GY/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailEntity(
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
        SearchItemEntity(
            parentId = "j_rFeI_A7L8gzrJjXAMd0FXXqIM",
            kind = "youtube#searchResult",
            etag = "elhoJXuEkd1u5KN1JMxXeotpjic",
            id = IdEntity(
                kind = "youtube#video",
                videoId = "SbTheNHE0VA"
            ),
            snippet = SearchSnippetEntity(
                publishedAt = "2023-10-07T11:28:49Z",
                channelId = "UCweWFpJ5CiJimQJAAx4aSew",
                title = "Did the 3rd dog eat cat nip? #dogs #dogsports #flyball",
                description = "",
                thumbnails = ThumbnailsEntity(
                    default = ThumbnailEntity(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/default.jpg",
                        width = 120,
                        height = 90
                    ),
                    medium = ThumbnailEntity(
                        url = "https://i.ytimg.com/vi/SbTheNHE0VA/mqdefault.jpg",
                        width = 320,
                        height = 180
                    ),
                    high = ThumbnailEntity(
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