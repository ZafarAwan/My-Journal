package com.bbox.myjournal.base

sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    class Failure(val msg: String) : ApiState<Nothing>()
    class Success<out T>(val data: T) : ApiState<T>()
    object Empty : ApiState<Nothing>()
}
