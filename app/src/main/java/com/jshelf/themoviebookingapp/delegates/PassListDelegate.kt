package com.jshelf.themoviebookingapp.delegates

import com.jshelf.themoviebookingapp.data.vos.CardVO

interface PassListDelegate {
    fun listDataReturn(count: List<CardVO>)
}