package com.jshelf.themoviebookingapp.model

import alirezat775.lib.carouselview.CarouselModel

class SampleModel(private var id: Int) : CarouselModel() {

    fun getId(): Int {
        return id
    }
}
