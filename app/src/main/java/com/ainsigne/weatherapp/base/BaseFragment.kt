package com.ainsigne.weatherapp.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.ainsigne.weatherapp.base.hilt.HiltFragmentEntryPoint

abstract class BaseFragment<VB : ViewBinding>(
    private val setUpViewBinding: (LayoutInflater) -> VB
) : HiltFragmentEntryPoint() {

    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setUpViewBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseActivity().window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowInsetsControllerCompat(baseActivity().window, baseActivity().window.decorView).apply {
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            isAppearanceLightStatusBars = false
        }
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setUpView()
        loadContent()
        addViewListeners()
    }

    fun baseActivity() = (requireActivity() as BaseActivity<*>)

    open fun setUpObserver() {}
    open fun loadContent() {}
    open fun setUpView() {}
    open fun addViewListeners() {}
}
