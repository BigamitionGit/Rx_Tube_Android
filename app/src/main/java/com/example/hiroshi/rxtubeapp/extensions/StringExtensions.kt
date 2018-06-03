package com.example.hiroshi.rxtubeapp.extensions

/**
 * Created on 2018/03/12.
 */

fun String.beginWithLowerCase(): String {
    return when (this.length) {
        0 -> throw(Exception("string is empty"))
        1 -> this.toLowerCase()
        else -> this[0].toLowerCase() + this.substring(1)
    }
}