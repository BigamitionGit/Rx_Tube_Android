package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import com.example.hiroshi.rxtubeapp.extensions.beginWithLowerCase

/**
 * Created on 2018/03/12.
 */
sealed class YoutubeApi {
}

data class YoutubeSearchRequire(val parameter: String = "part", val properties: Set<Property>) {
    enum class Property {
        snippet,
        id
    }
}

interface ParameterType {
    val parameter: String
        get() {
            return this.toString().beginWithLowerCase()
        }
}

sealed class YoutubeSearchFilter {
    data class ForContentOwner(val forContentOwner: Boolean): YoutubeSearchFilter(), ParameterType
    data class ForMine(val forMine: Boolean): YoutubeSearchFilter(), ParameterType
    data class RelatedToVideoId(val id: String): YoutubeSearchFilter(), ParameterType

    val property: Any
        get() {
            return when (this) {
                is ForContentOwner -> this.forContentOwner
                is ForMine -> this.forMine
                is RelatedToVideoId -> this.id
            }
        }


}