/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbox.myjournal.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T: Any?>(protected val content: T) {

    var hasBeenHandled = false
        protected set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    open fun getContentIfNotHandled(): T {
        return if (hasBeenHandled) {
            throw EventHandledException()
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    open fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T: Any?>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        try {
            if (event != null) {
                onEventUnhandledContent(event.getContentIfNotHandled())
            }
        } catch (ehe: EventHandledException) {
            // Do nothing as event is already handled
        }
    }
}

class EventHandledException(message: String? = "Content already handled") : Exception(message)

class MutableEvent<T: Any?>(value: T? = null) : MutableLiveData<Event<T>>(if (value == null) null else Event(value)) {
    fun postEvent(value: T) {
        postValue(Event(value))
    }

    fun setEvent(value: T) {
        setValue(Event(value))
    }
}

fun <T, V> LiveData<T>.mapEvent(map: (T) -> V): LiveData<Event<V>> {
    return Transformations.map(this) {
        Event(map(it))
    }
}

fun <T : Any?> Fragment.observeEvent(
    liveData: LiveData<Event<T>>,
    observe: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner, EventObserver {
        observe.invoke(it)
    })
}