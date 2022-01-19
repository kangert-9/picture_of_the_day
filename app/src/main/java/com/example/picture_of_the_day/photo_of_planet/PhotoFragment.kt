package com.example.picture_of_the_day.photo_of_planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picture_of_the_day.databinding.FragmentPhotoBinding


class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val viewPager = binding.viewPager
        viewPager.adapter= ViewPagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(viewPager)
        return binding.root
    }
}