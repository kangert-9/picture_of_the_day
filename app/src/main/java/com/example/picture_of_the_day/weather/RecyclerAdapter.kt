package com.example.picture_of_the_day.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.picture_of_the_day.R

class RecyclerAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<WeatherData>
):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_PLANET) {
            PlanetViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_planet, parent,
                    false) as View
            )
        } else {
            SateliteViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_satelite, parent,
                    false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        if (getItemViewType(position) == TYPE_PLANET) {
            holder as PlanetViewHolder
            holder.bind(data[position])
        } else {
            holder as SateliteViewHolder
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].someDescription.isNullOrBlank()) TYPE_SATELITE else
            TYPE_PLANET
    }


    inner class PlanetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val wikiImageView = view.findViewById<ImageView>(R.id.wikiImageView)

        fun bind(data: WeatherData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                descriptionTextView.text = data.someDescription
                wikiImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    inner class SateliteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val moonImageView = view.findViewById<ImageView>(R.id.moonImageView)

        fun bind(data: WeatherData) {
            moonImageView.setOnClickListener {
                onListItemClickListener.onItemClick(data) }
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