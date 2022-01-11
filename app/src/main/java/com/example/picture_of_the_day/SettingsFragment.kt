package com.example.picture_of_the_day

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.picture_of_the_day.databinding.FragmentBottomNavigationDrawerBinding
import com.example.picture_of_the_day.databinding.FragmentSettingsBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class SettingsFragment : Fragment() {
   // private var _binding: FragmentSettingsBinding? = null
  //  private val binding get() = _binding!!
      private lateinit var chipGroup: ChipGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chipGroup = view?.findViewById<ChipGroup>(R.id.chipGroup)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
//        return binding.root
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                Toast.makeText(context, "Выбран ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}