package com.ainsigne.weatherapp.ui.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ainsigne.weatherapp.base.BaseFragment
import com.ainsigne.weatherapp.databinding.MainFragmentBinding
import com.ainsigne.weatherapp.ui.main.adapter.DailyForecastsAdapter
import kotlinx.coroutines.flow.collect

class MainFragment : BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    var adapter : DailyForecastsAdapter? = null

    override fun setUpView() {
        super.setUpView()
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.refreshContent()
            }
            adapter = DailyForecastsAdapter(onClick = { vhValues,
                                                        dailyWeather,
                                                        currentWeather ->
                dailyWeather?.also {
                    findNavController().navigate(MainFragmentDirections.mainToDetails(
                        dt = it.dt,
                        width = vhValues.getViewWidth(),
                        height = vhValues.getViewHeight(),
                        yAxis = vhValues.getY(),
                        xAxis = vhValues.getX(),
                        isDaily = true
                    ))
                }
                currentWeather?.also {
                    findNavController().navigate(MainFragmentDirections.mainToDetails(
                        dt = it.dt,
                        width = vhValues.getViewWidth(),
                        height = vhValues.getViewHeight(),
                        yAxis = vhValues.getY(),
                        xAxis = vhValues.getX(),
                        isDaily = false
                    ))
                }
            })
            rvDailyWeathers.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshContent()
    }


    override fun setUpObserver() {
        super.setUpObserver()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.dailyWeatherState.collect { state ->
                when (state) {
                    DailyWeatherState.ShowLoader -> {
                        binding.swipeRefresh.isRefreshing = true
                    }
                    DailyWeatherState.HideLoader -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                    is DailyWeatherState.Error -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                    is DailyWeatherState.SuccessDailyWeatherDisplay -> {
                        binding.swipeRefresh.isRefreshing = false
                        adapter?.updateDailyForecasts(state.dailyWeathers)
                        adapter?.notifyDataSetChanged()
                    }
                    is DailyWeatherState.SuccessCurrentWeatherDisplay -> {
                        binding.swipeRefresh.isRefreshing = false
                        adapter?.updateCurrentWeather(state.currentWeather)
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}