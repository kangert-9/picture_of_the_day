package com.example.picture_of_the_day.weather


import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.picture_of_the_day.databinding.FragmentMainStartBinding
import com.example.picture_of_the_day.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val data = arrayListOf(
            WeatherData("Earth"),
            WeatherData("Mars", ""),
            WeatherData("Луна", "")
        )
        val adapter = RecyclerAdapter(
            object : RecyclerAdapter.OnListItemClickListener {
                override fun onItemClick(data: WeatherData) {
                    Toast.makeText(requireContext(), data.someText,
                        Toast.LENGTH_SHORT).show()
                }
            },
            data
        )
        binding.recyclerView.adapter = adapter
        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.recyclerView)

        return binding.root
    }
}