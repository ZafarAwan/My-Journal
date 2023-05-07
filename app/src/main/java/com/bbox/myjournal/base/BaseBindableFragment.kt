package com.bbox.myjournal.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseBindableFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T
        private set

    protected abstract val layout: Int
        @LayoutRes get

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        onBind()
        return binding.root
    }

    protected abstract fun onBind()
}