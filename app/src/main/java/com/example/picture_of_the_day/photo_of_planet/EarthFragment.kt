package com.example.picture_of_the_day.photo_of_planet

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.picture_of_the_day.R
import com.example.picture_of_the_day.databinding.FragmentEarthBinding


class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    private var show = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        binding.backgroundImage.setOnClickListener {
            if (show)
                hideComponents()
            else
                showComponents()
        }
        val spannable = SpannableString(binding.title.text)
        spannable.setSpan(
            ForegroundColorSpan(Color.GREEN),
            0, 7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.title.text =spannable
        return binding.root
    }

    private fun hideComponents() {
        show = false
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_earth)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(binding.constraintContainer,
            transition)
        constraintSet.applyTo(binding.constraintContainer)
    }

    private fun showComponents() {
        show = true
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.earth_end)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(binding.constraintContainer,
            transition)
        constraintSet.applyTo(binding.constraintContainer)
    }
}