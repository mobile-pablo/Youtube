package com.mobile.pablo.storage.sharedprefs

import androidx.annotation.Keep
import com.mobile.pablo.core.util.EMPTY_STRING

@Keep
enum class Setting(val type: Type) {
    OAUTH_TOKEN(Type.STRING),
    DARK_MODE_ENABLED(Type.BOOL),

    TEST_BOOL(Type.BOOL),
    TEST_INT(Type.INT),
    TEST_LONG(Type.LONG),
    TEST_STRING(Type.STRING)
}

enum class Type(val default: Any) {
    BOOL(false),
    INT(0),
    LONG(0L),
    STRING(EMPTY_STRING)
}