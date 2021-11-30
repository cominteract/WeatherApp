package com.ainsigne.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.ainsigne.weatherapp.base.hilt.HiltActivityEntryPoint

abstract class BaseActivity<VB : ViewBinding>(
    private val setUpViewBinding: (LayoutInflater) -> VB
) : HiltActivityEntryPoint() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setUpViewBinding(layoutInflater)
        setContentView(binding.root)

        setUpObserver()
        setUpView()
        loadContent()
    }

    open fun setUpObserver() {}
    open fun loadContent() {}
    open fun setUpView() {}

}
