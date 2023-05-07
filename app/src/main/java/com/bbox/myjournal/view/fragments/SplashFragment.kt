package com.bbox.myjournal.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bbox.myjournal.R
import com.bbox.myjournal.base.BaseBindableFragment
import com.bbox.myjournal.databinding.FragmentSplashBinding
import com.bbox.myjournal.utils.Utils.navigateToMasterScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseBindableFragment<FragmentSplashBinding>() {

    override val layout: Int
        get() = R.layout.fragment_splash

    override fun onBind() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.postDelayed({
            navigateToMasterScreen(SplashFragmentDirections.toMainFragment())
        }, 2000)
    }

}