package com.example.picture_of_the_day

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.picture_of_the_day.databinding.ActivityMainBinding
import com.example.picture_of_the_day.photo_of_planet.PhotoFragment
import com.example.picture_of_the_day.picture_of_the_day.PictureOfTheDayFragment
import com.example.picture_of_the_day.weather.WeatherFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val nameSharedPreference = "LOGIN"

    private lateinit var binding: ActivityMainBinding

    private val appTheme = "APP_THEME"
    private val Theme_Picture_of_the_day = 0
    private val Moon = 1
    private val Mars = 2
    private val Cosmos = 3

    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(getAppTheme(R.style.Theme_Picture_of_the_day))
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container,
                            PictureOfTheDayFragment()
                        )
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_photos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container,
                            PhotoFragment()
                        )
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    binding.fab.visibility=View.GONE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container,
                            WeatherFragment()
                        )
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.home
        setFAB(binding.fab)
    }

    private fun setFAB(fab: FloatingActionButton) {
        setInitialState()
        fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }
    }

    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, 225f).start()
        ObjectAnimator.ofFloat(binding.optionFourContainer, "translationY",
            -130f).start()
        ObjectAnimator.ofFloat(binding.optionThreeContainer, "translationY",
            -260f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY",
            -390f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY",
            -520f).start()
        binding.optionFourContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionFourContainer.isClickable = true
                    binding.optionFourContainer.setOnClickListener {
                        theme(Cosmos)
                    }
                }
            })
        binding.optionThreeContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionThreeContainer.isClickable = true
                    binding.optionThreeContainer.setOnClickListener {
                        theme(Moon)
                    }
                }
            })
        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        theme(Theme_Picture_of_the_day)
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        theme(Mars)
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFab() {
        isExpanded = false
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, -180f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionThreeContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionFourContainer, "translationY", 0f).start()
        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }

    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha = 0f
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionThreeContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionFourContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    fun theme( myCoolCodeStyle: Int) {
            setAppTheme(myCoolCodeStyle)
            recreate()
    }

    private fun setAppTheme(myCoolCodeStyle: Int) {
        val sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(appTheme, myCoolCodeStyle)
        editor.apply()
    }

    private fun getAppTheme(myStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(myStyle))
    }

    private fun getCodeStyle(myStyle: Int): Int {
        val sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE)
        return sharedPref.getInt(appTheme, myStyle)
    }

    private fun codeStyleToStyleId(codeStyle: Int): Int {
        return when (codeStyle) {
            Mars -> R.style.MarsStyle
            Moon -> R.style.MoonStyle
            Cosmos -> R.style.CosmosStyle
            else -> R.style.Theme_Picture_of_the_day
        }
    }
}