package com.example.picture_of_the_day.weather

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.picture_of_the_day.R

class RecyclerAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<WeatherData>
):
    RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_PLANET) {
            PlanetViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_planet, parent,
                    false) as View
            )
        } else {
            SatelliteViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_satelite, parent,
                    false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].description.isNullOrBlank()) TYPE_PLANET else
            TYPE_SATELITE
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else
                toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }
    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class PlanetViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        val planetImageView = view.findViewById<ImageView>(R.id.earthImageView)
        val moveItemDown = view.findViewById<ImageView>(R.id.moveItemDown)
        val moveItemUp = view.findViewById<ImageView>(R.id.moveItemUp)
        val planetName = view.findViewById<TextView>(R.id.planetName)
        val planetWeather = view.findViewById<TextView>(R.id.planetWeather)

        override fun bind(data: WeatherData) {
                planetName.text = data.name
                planetWeather.text = data.weather
                planetImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
                moveItemDown.setOnClickListener { moveDown() }
                moveItemUp.setOnClickListener { moveUp() }
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }
        private fun moveDown() {
            layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }
        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class SatelliteViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        val moonImageView = view.findViewById<ImageView>(R.id.moonImageView)
        val moveItemDown = view.findViewById<ImageView>(R.id.moveItemDown)
        val moveItemUp = view.findViewById<ImageView>(R.id.moveItemUp)
        val name = view.findViewById<TextView>(R.id.name)
        val descr = view.findViewById<TextView>(R.id.descr)
        val weather = view.findViewById<TextView>(R.id.weather)

        override fun bind(data: WeatherData) {
            name.text = data.name
            descr.text = data.description
            weather.text = data.weather
            moonImageView.setOnClickListener {
                onListItemClickListener.onItemClick(data) }
            moveItemDown.setOnClickListener { moveDown() }
            moveItemUp.setOnClickListener { moveUp() }
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }
        private fun moveDown() {
            layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }
        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

            interface OnListItemClickListener {
        fun onItemClick(data: WeatherData)
    }
    companion object {
        private const val TYPE_PLANET = 0
        private const val TYPE_SATELITE = 1
    }

}