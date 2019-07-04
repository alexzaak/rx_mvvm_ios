package codes.zaak.showcase_mvvm_android.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.zaak.showcase_mvvm_android.R
import codes.zaak.showcase_mvvm_android.weather.data.Forecast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast_row.view.*

class ForecastAdapter() :
    ListAdapter<Forecast, ForecastAdapter.ViewHolder>(ForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_forecast_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: Forecast) {
            itemView.state.text = forecast.state
            Picasso.get()
                .load("https://www.metaweather.com/static/img/weather/png/64/${forecast.state}.png")
                .into(itemView.icon)
        }
    }
}

class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.state == newItem.state
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }
}