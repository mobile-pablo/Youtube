package com.mobile.pablo.core.ext

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

inline fun <reified T> T.toJson(moshi: Moshi): String = moshi.adapter(T::class.java).toJson(this)

@Throws(IllegalStateException::class)
inline fun <reified T> String.fromJson(moshi: Moshi): T =
    moshi.adapter(T::class.java).fromJson(this)
        ?: throw IllegalStateException("Cannot convert $this to type ${T::class.java.simpleName}")

inline fun <reified T> String.fromJsonList(moshi: Moshi): List<T> {
    val type: Type = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter = moshi.adapter<List<T>>(type)
    return adapter.fromJson(this) ?: listOf()
}