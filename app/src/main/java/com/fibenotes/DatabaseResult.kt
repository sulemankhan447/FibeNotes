package com.fibenotes

sealed class DatabaseResult<T>(val data: T? = null, val message: String? = "") {
    class Success<T>(data: T) : DatabaseResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : DatabaseResult<T>(data)
    class Loading<T> : DatabaseResult<T>()
}
