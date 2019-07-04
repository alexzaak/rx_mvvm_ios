package codes.zaak.showcase_mvvm_android.locationlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.zaak.showcase_mvvm_android.R
import codes.zaak.showcase_mvvm_android.locationlist.data.Location
import kotlinx.android.synthetic.main.item_location_row.view.*

class LocationAdapter(private val clickListener: (Location) -> Unit) :
    ListAdapter<Location, LocationAdapter.ViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_location_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(location: Location, clickListener: (Location) -> Unit) {
            itemView.location.text = location.title
            itemView.setOnClickListener { clickListener(location) }
        }
    }
}