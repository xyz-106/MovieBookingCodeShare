package com.jshelf.themoviebookingapp.model

import alirezat775.lib.carouselview.CarouselModel

class EmptySampleModel constructor(private val text: String) : CarouselModel() {

    fun getText(): String {
        return text
    }
}