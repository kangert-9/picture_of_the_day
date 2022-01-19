package com.example.picture_of_the_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picture_of_the_day.databinding.ActivityMainBinding
import com.example.picture_of_the_day.photo_of_planet.PhotoFragment
import com.example.picture_of_the_day.picture_of_the_day.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    private val nameSharedPreference = "LOGIN"

    private lateinit var binding: ActivityMainBinding

    private val appTheme = "APP_THEME"
    private val Moon = 1
    private val Mars = 2
    private val Cosmos = 3

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
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container,
                            WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.fab.setOnClickListener {
           supportFragmentManager.beginTransaction().add(R.id.activity_api_bottom_container,
                    ChipsFragment.newInstance()).
                      addToBackStack(null).
                commit()
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
        //Toast.makeText(applicationContext, getCodeStyle(myStyle).toString(), LENGTH_SHORT).show()
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