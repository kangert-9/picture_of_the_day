package com.example.picture_of_the_day

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class ChipsFragment : Fragment() {
    private val appTheme = "AppTheme"
    private val MyCoolCodeStyle = 0
    private val AppThemeLightCodeStyle = 1
    private val AppThemeCodeStyle = 2
    private val AppThemeDarkCodeStyle = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chips, container, false)

        initThemeChooser(view)
//        val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroup)

//        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
//            chipGroup.findViewById<Chip>(position)?.let {
//                val act = activity as MainActivity
//                act.initThemeChooser(it)
//            }
//        }
        return view
    }

    private fun initThemeChooser(view: View) {
        initChips(view.findViewById(R.id.chip_earth), MyCoolCodeStyle)
        initChips(view.findViewById(R.id.chip_cosmos), AppThemeDarkCodeStyle)
        initChips(view.findViewById(R.id.chip_moon), AppThemeLightCodeStyle)
        initChips(view.findViewById(R.id.chip_mars), AppThemeCodeStyle)
        val rg = view.findViewById<ChipGroup>(R.id.chipGroup)
       // (rg.getChildAt(getCodeStyle(MyCoolCodeStyle))).setChecked(true)
    }

    private fun initChips(chip: Chip, myCoolCodeStyle: Int) {
        chip.setOnClickListener {
            val act = activity as MainActivity
            act.theme(myCoolCodeStyle)
        }
    }

//    private fun getCodeStyle(myCoolCodeStyle: Int): Int {
//        val act = activity as MainActivity
//    }

    companion object {
        fun newInstance() = ChipsFragment()
    }
}