package com.example.hiroshi.rxtubeapp.ui.searchitems

/**
 * Created on 2018/03/16.
 */


class SearchItemDataSource(var viewModels: List<ViewModel>) {

    sealed class ViewModel {

        abstract fun getViewType(): ViewType

        enum class ViewType(val id: Int) {
            Video(10),
            Channel(20);

            companion object {
                fun from(id: Int): ViewType {
                    return values().first { it.id == id }
                }
            }

        }

        data class Video(val title: String):ViewModel() {
            override fun getViewType(): ViewType {
                return ViewType.Video
            }
        }
        data class Channel(val title: String):ViewModel() {
            override fun getViewType(): ViewType {
                return ViewType.Channel
            }
        }


    }
}