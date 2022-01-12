package com.example.picture_of_the_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    private val sm = supportFragmentManager
    private val nameSharedPreference = "LOGIN"

    private val appTheme = "APP_THEME"
    private val MyCoolCodeStyle = 0
    private val AppThemeLightCodeStyle = 1
    private val AppThemeCodeStyle = 2
    private val AppThemeDarkCodeStyle = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(R.style.MyCoolStyle))
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            sm.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
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

    private fun getAppTheme(myCoolStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(myCoolStyle))
    }

    private fun getCodeStyle(myCoolStyle: Int): Int {
        val sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE)
        Log.d("dm", sharedPref.getInt(appTheme, myCoolStyle).toString())
        return sharedPref.getInt(appTheme, myCoolStyle)
    }

    private fun codeStyleToStyleId(codeStyle: Int): Int {
        return when (codeStyle) {
            AppThemeCodeStyle -> R.style.AppTheme
            AppThemeLightCodeStyle -> R.style.AppThemeLight
            AppThemeDarkCodeStyle -> R.style.AppThemeDark
            else -> R.style.MyCoolStyle
        }
    }
}