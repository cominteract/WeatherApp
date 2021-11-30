package com.ainsigne.weatherapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import com.ainsigne.utilities.hourFormatToDisplay
import com.ainsigne.weatherapp.R
import com.ainsigne.weatherapp.databinding.ItemCurrentWeatherBinding
import com.ainsigne.weatherapp.databinding.ItemDailyWeathersBinding
import com.ainsigne.weatherapp.helper.iconToBackground
import com.ainsigne.weatherapp.helper.iconToDrawable
import com.google.android.material.shape.CornerFamily

class DailyForecastsAdapter(val onClick: (ViewHolderValues, DailyWeather?, CurrentWeather?) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val currentWeatherViewType = 1
    val dailyWeatherViewType = 2


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            currentWeatherViewType
        } else {
            dailyWeatherViewType
        }
    }

    var dailyWeathers: List<DailyWeather> = emptyList()

    fun updateDailyForecasts(dailyWeathers: List<DailyWeather>) {
        this.dailyWeathers = dailyWeathers
    }

    var currentWeather: CurrentWeather? = null

    fun updateCurrentWeather(currentWeather: CurrentWeather) {
        this.currentWeather = currentWeather
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                if (viewType == dailyWeatherViewType) R.layout.item_daily_weathers else R.layout.item_current_weather,
                parent,
                false)
        return if (viewType == dailyWeatherViewType) {
            DailyWeatherViewHolder(
                ItemDailyWeathersBinding.bind(view), onClick
            )
        } else {
            CurrentWeatherViewHolder(
                ItemCurrentWeatherBinding.bind(view), onClick
            )
        }
    }

    override fun getItemCount(): Int = dailyWeathers.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == dailyWeatherViewType) {
            (holder as DailyWeatherViewHolder?)?.bind(dailyWeathers[position - 1])
        } else {
            currentWeather?.let { (holder as CurrentWeatherViewHolder?)?.bind(it) }
        }
    }

    class CurrentWeatherViewHolder(val binding: ItemCurrentWeatherBinding,
                                   val onClick: (ViewHolderValues, DailyWeather?, CurrentWeather?) -> Unit) :
        RecyclerView.ViewHolder(binding.root), ViewHolderValues {
        fun bind(currentWeather: CurrentWeather) {
            binding.tvSunrise.text = itemView.resources.getString(R.string.sunrise_text, currentWeather.sunrise.hourFormatToDisplay())
            binding.tvSunset.text = itemView.resources.getString(R.string.sunset_text, currentWeather.sunset.hourFormatToDisplay())
            binding.tvDescription.text = currentWeather.description
            binding.tvDate.text = currentWeather.displayDate
            binding.tvTimezone.text = currentWeather.timezone
            binding.ivDescription.setImageResource(currentWeather.icon.iconToDrawable())
            binding.root.setOnClickListener {
                onClick(this, null, currentWeather)
            }
            // binding.bgWeather.setBackgroundColor(ContextCompat.getColor(itemView.context,currentWeather.icon.iconToBackground()))
        }

        override fun getViewHeight(): Int {
            return binding.root.height
        }

        override fun getViewWidth(): Int {
            return binding.root.width
        }

        override fun getY(): Int {
            return binding.root.y.toInt()
        }

        override fun getX(): Int {
            return binding.root.x.toInt()
        }
    }

    class DailyWeatherViewHolder(val binding: ItemDailyWeathersBinding, val onClick: (ViewHolderValues, DailyWeather?, CurrentWeather?) -> Unit) :
        RecyclerView.ViewHolder(binding.root), ViewHolderValues  {
        fun bind(dailyWeather: DailyWeather) {
            binding.tvDay.text = itemView.resources.getString(R.string.day_temp_text, dailyWeather.dayTemp.toString())
            binding.tvNight.text = itemView.resources.getString(R.string.night_temp_text, dailyWeather.nightTemp.toString())
            binding.tvDescription.text = dailyWeather.description
            binding.tvDate.text = dailyWeather.displayDate
            val radius: Float = itemView.resources.getDimension(R.dimen.default_corner_radius)
            binding.ivDay.setShapeAppearanceModel(
                binding.ivDay.getShapeAppearanceModel()
                    .toBuilder()
                    .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
                    .build()
            )
            binding.ivNight.setShapeAppearanceModel(
                binding.ivNight.getShapeAppearanceModel()
                    .toBuilder()
                    .setBottomRightCorner(CornerFamily.ROUNDED, radius)
                    .build()
            )
            binding.ivDescription.setImageResource(dailyWeather.icon.iconToDrawable())
            binding.bgWeather.setBackgroundColor(ContextCompat.getColor(itemView.context,dailyWeather.icon.iconToBackground()))
            binding.root.setOnClickListener {
                onClick(this, dailyWeather, null)
            }

        }
        override fun getViewHeight(): Int {
            return binding.root.height
        }

        override fun getViewWidth(): Int {
            return binding.root.width
        }

        override fun getY(): Int {
            return binding.root.y.toInt()
        }

        override fun getX(): Int {
            return binding.root.x.toInt()
        }
    }

    /**
     * [ViewHolderValues] for holding x and y position
     */
    interface ViewHolderValues {
        /**
         * @return [Int] the view height
         */
        fun getViewHeight(): Int
        /**
         * @return [Int] the view width
         */
        fun getViewWidth(): Int
        /**
         * @return [Int] the y axis of the current view
         */
        fun getY(): Int
        /**
         * @return [Int] the x axis of the current view
         */
        fun getX(): Int
    }
}
