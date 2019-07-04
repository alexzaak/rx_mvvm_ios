package codes.zaak.showcase_mvvm_android.locationlist.ui

import androidx.recyclerview.widget.DiffUtil
import codes.zaak.showcase_mvvm_android.locationlist.data.Location

class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}