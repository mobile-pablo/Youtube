package com.mobile.pablo.search.data

import com.mobile.pablo.core.util.EMPTY_STRING

data class VoiceToTextParserState(
    val isSpeaking: Boolean = false,
    val spokenText: String = EMPTY_STRING,
    val error: String? = null
)
