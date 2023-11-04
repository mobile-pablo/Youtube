package com.mobile.pablo.storage.sharedprefs

import com.mobile.pablo.core.util.EMPTY_STRING

enum class Setting(val type: Type) {
    OAUTH_TOKEN(Type.STRING),
    DARK_MODE_ENABLED(Type.BOOL),
    NEXT_PAGE_TOKEN(Type.STRING),
    PREV_PAGE_TOKEN(Type.STRING),
}

enum class Type(val default: Any) {
    BOOL(false),
    INT(0),
    LONG(0L),
    STRING(EMPTY_STRING)
}