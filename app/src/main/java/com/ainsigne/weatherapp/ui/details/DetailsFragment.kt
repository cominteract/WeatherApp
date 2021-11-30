package com.ainsigne.weatherapp.ui.details

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Handler
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import com.ainsigne.utilities.hourFormatToDisplay
import com.ainsigne.weatherapp.R
import com.ainsigne.weatherapp.base.BaseFragment
import com.ainsigne.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.ainsigne.weatherapp.helper.iconToDrawable
import kotlinx.coroutines.flow.collect


/**
 * Animation duration constant for motion layout transitions
 */
private const val ANIMATION_DURATION = 500


class DetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>(FragmentWeatherDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels()


    private val args : DetailsFragmentArgs by navArgs()

    /**
     * constraint set id for end transition
     */
    var mConstraintEnd = R.id.end
    /**
     * constraint set id for start set transition
     */
    var mConstraintStartSet = R.id.start_set
    /**
     * constraint set id for mid set transition
     */
    var mConstraintMidSet = R.id.mid_set
    /**
     * constraint set id for end set transition
     */
    var mConstraintEndSet = R.id.end_set
    /**
     * constraint set id for mid end set transition
     */
    var mConstraintMidEndSet = R.id.mid_end_set


    /**
     * Motionlayout for weather details related transition
     */
    private lateinit var animatedView: MotionLayout
    /**
     * Motionlayout for weather display related transitin
     */
    private lateinit var swipeView: MotionLayout

    override fun setUpView() {
        super.setUpView()
        binding.apply {
            //rvWeatherDetails.adapter = adapter
            animatedView = binding.motionLayout
            swipeView = binding.swipeMotionLayout
            WindowInsetsControllerCompat(baseActivity().window, baseActivity().window.decorView).apply {
                hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    override fun setUpObserver() {
        super.setUpObserver()
        if (args.isDaily) {
            viewModel.watchDailyWeatherDetails(args.dt)
        } else {
            viewModel.watchCurrentWeatherDetails(args.dt)
        }
        binding.viewOverlay.visibility = View.VISIBLE
        binding.viewOverlay.animate().alpha(0.0f).setDuration(900)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.weatherDetailsState.collect { state ->
                when (state) {
                    WeatherDetailsState.ShowLoader -> {

                    }
                    WeatherDetailsState.HideLoader -> {

                    }
                    is WeatherDetailsState.Error -> {

                    }
                    is WeatherDetailsState.SuccessDailyWeatherDisplay -> {
                        binding.ivThumbnail.background = ContextCompat.getDrawable(baseActivity(), state.dailyWeather.icon.iconToDrawable())
                        bindDailyWeatherVisibility()
                        bindAnimation(args)
                        bindDailyWeather(state.dailyWeather)
                    }
                    is WeatherDetailsState.SuccessCurrentWeatherDisplay -> {
                        binding.ivThumbnail.background = ContextCompat.getDrawable(baseActivity(), state.currentWeather.icon.iconToDrawable())
                        bindCurrentWeatherVisibility()
                        bindAnimation(args)
                        bindCurrentWeather(state.currentWeather)
                    }
                }
            }
        }
    }

    /**
     * bind motion layout animations for better user experience
     */
    private fun bindAnimation(it: DetailsFragmentArgs){

        var currentConstraintSet = mConstraintStartSet
        var activeConstraintSet = mConstraintMidSet
        if(it.xAxis > 200) {
            currentConstraintSet = mConstraintEndSet
            activeConstraintSet = mConstraintMidEndSet
        }

        animatedView.also { ml ->
            var constraintSet = ml.getConstraintSet(activeConstraintSet)
            constraintSet.setMargin(R.id.iv_thumbnail, ConstraintSet.TOP, it.yAxis)
            constraintSet.applyTo(ml)
            ml.setTransition(currentConstraintSet, activeConstraintSet)
            ml.setTransitionDuration(ANIMATION_DURATION)
            ml.transitionToState(currentConstraintSet)
            Handler().postDelayed({
                constraintSet = ml.getConstraintSet(activeConstraintSet)
                constraintSet.setMargin(R.id.iv_thumbnail, ConstraintSet.TOP, it.yAxis)
                constraintSet.applyTo(ml)
                constraintSet = ml.getConstraintSet(mConstraintEnd)
                constraintSet.setMargin(R.id.iv_thumbnail, ConstraintSet.TOP, 0)
                constraintSet.applyTo(ml)
                ml.setTransition(activeConstraintSet, mConstraintEnd)
                ml.setTransitionDuration(ANIMATION_DURATION)
                ml.transitionToState(mConstraintEnd)
                val colorFrom = Color.parseColor("#00000000")
                val colorTo = ContextCompat.getColor(
                    baseActivity(),
                    R.color.white
                )
                val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
                colorAnimation.duration = 500 // milliseconds
                colorAnimation.addUpdateListener { animator ->
                    binding.container.setBackgroundColor(animator.animatedValue as Int)

                }
                colorAnimation.start()
            }, 100)
        }
    }

    fun bindDailyWeather(dailyWeather: DailyWeather) {
        binding.tvTimezone.text = dailyWeather.timezone
        binding.tvClouds.visibility = if (dailyWeather.clouds > 0.0) View.VISIBLE else View.GONE
        binding.tvPressure.visibility = if (dailyWeather.pressure > 0.0) View.VISIBLE else View.GONE
        binding.tvHumidity.visibility = if (dailyWeather.humidity > 0.0) View.VISIBLE else View.GONE
        binding.tvHumidity.text = getString(R.string.humidity_temp_text, dailyWeather.humidity.toString())
        binding.tvClouds.text = getString(R.string.cloud_temp_text, dailyWeather.clouds.toString())
        binding.tvPressure.text = getString(R.string.pressure_temp_text, dailyWeather.pressure.toString())
        binding.tvName.text = dailyWeather.name
        binding.tvDescription.text = dailyWeather.description
        binding.tvDate.text = dailyWeather.displayDate
        binding.tvDay.text = getString(R.string.day_temp_text, dailyWeather.dayTemp.toString())
        binding.tvNight.text = getString(R.string.night_temp_text, dailyWeather.nightTemp.toString())

    }

    fun bindDailyWeatherVisibility() {
        binding.tvDay.visibility = View.VISIBLE
        binding.tvNight.visibility = View.VISIBLE
        binding.ivDay.visibility = View.VISIBLE
        binding.ivNight.visibility = View.VISIBLE
        binding.tvSunrise.visibility = View.GONE
        binding.tvSunset.visibility = View.GONE
        binding.ivSunriseSunset.visibility = View.GONE
    }

    fun bindCurrentWeatherVisibility() {
        binding.tvDay.visibility = View.GONE
        binding.tvNight.visibility = View.GONE
        binding.ivDay.visibility = View.GONE
        binding.ivNight.visibility = View.GONE
        binding.tvSunrise.visibility = View.VISIBLE
        binding.tvSunset.visibility = View.VISIBLE
        binding.ivSunriseSunset.visibility = View.VISIBLE
    }

    fun bindCurrentWeather(currentWeather: CurrentWeather) {
        binding.tvTimezone.text = currentWeather.timezone
        binding.tvClouds.visibility = if (currentWeather.clouds > 0.0) View.VISIBLE else View.GONE
        binding.tvPressure.visibility = if (currentWeather.pressure > 0.0) View.VISIBLE else View.GONE
        binding.tvHumidity.visibility = if (currentWeather.humidity > 0.0) View.VISIBLE else View.GONE
        binding.tvHumidity.text = getString(R.string.humidity_temp_text, currentWeather.humidity.toString())
        binding.tvClouds.text = getString(R.string.cloud_temp_text, currentWeather.clouds.toString())
        binding.tvPressure.text = getString(R.string.pressure_temp_text, currentWeather.pressure.toString())
        binding.tvName.text = currentWeather.name
        binding.tvDescription.text = currentWeather.description
        binding.tvDate.text = currentWeather.displayDate
        binding.tvSunrise.text = getString(R.string.sunrise_text, currentWeather.sunrise.hourFormatToDisplay())
        binding.tvSunset.text = getString(R.string.sunset_text, currentWeather.sunset.hourFormatToDisplay())
    }
}