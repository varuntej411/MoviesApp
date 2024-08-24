package com.openplay.tech.myapplication.commonutils

sealed class APIDataStatus<out T>(val data: T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T?) : APIDataStatus<T>(data)
    class ERROR<T>(message: String, data: T? = null) : APIDataStatus<T>(data, message)
    class LOADING<T>(data: T? = null) : APIDataStatus<T>(data)
}