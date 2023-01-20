package com.fibenotes.listener

interface ValidationListener {

    fun onFailure(msg: Int)
    fun onSuccess()
}