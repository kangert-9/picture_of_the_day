package com.example.picture_of_the_day.weather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
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
            WeatherData(name ="Меркурий", weather = "+427"),
            WeatherData(name ="Венера", weather = "+462"),
            WeatherData(name ="Земля", weather = "+15"),
            WeatherData(name ="Луна", description = "спутник Земли", "-49"),
            WeatherData(name ="Марс", weather = "−63"),
            WeatherData(name ="Фобос", description = "спутник Марса", "-223К"),
            WeatherData(name ="Юпитер", weather = "+24000"),
            WeatherData(name ="Европа", description = "спутник Юпитера", "-110К"),
            WeatherData(name ="Ио", description = "спутник Юпитера", "-110К")
        )
        val adapter = RecyclerAdapter(
            object : RecyclerAdapter.OnListItemClickListener {
                override fun onItemClick(data: WeatherData) {
                    Toast.makeText(requireContext(), data.name,
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