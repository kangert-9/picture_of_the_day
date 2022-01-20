package com.example.picture_of_the_day.photo_of_planet

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.picture_of_the_day.R
import com.example.picture_of_the_day.databinding.FragmentEarthBinding
import com.example.picture_of_the_day.databinding.FragmentMainStartBinding


class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        val im = binding.image
        im.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                container, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )
            val params: ViewGroup.LayoutParams = im.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else
                    ViewGroup.LayoutParams.WRAP_CONTENT
            im.layoutParams = params
            im.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else
                    ImageView.ScaleType.FIT_CENTER
        }
        return binding.root
    }
}