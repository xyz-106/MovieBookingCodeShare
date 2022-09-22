package com.jshelf.themoviebookingapp.delegates

interface PassDataDelegate {
    fun onPassSignUpData(email: String, password: String, name: String, phone: String)
    fun onPassLoginData(email: String, password: String)
}