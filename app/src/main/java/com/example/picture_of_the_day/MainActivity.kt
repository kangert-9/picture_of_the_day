package com.example.picture_of_the_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class MainActivity : AppCompatActivity() {
    private val sm = supportFragmentManager
    private val nameSharedPreference = "LOGIN"

    private val appTheme = "APP_THEME"
    private val Theme_Picture_of_the_day = 0
    private val Moon = 1
    private val Mars = 2
    private val Cosmos = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(R.style.Theme_Picture_of_the_day))
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