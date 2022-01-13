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
    private val Theme_Picture_of_the_day = 0
    private val Moon = 1
    private val Mars = 2
    private val Cosmos = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chips, container, false)

        initThemeChooser(view)
        return view
    }

    private fun initThemeChooser(view: View) {
        initChips(view.findViewById(R.id.chip_earth), Theme_Picture_of_the_day)
        initChips(view.findViewById(R.id.chip_cosmos), Cosmos)
        initChips(view.findViewById(R.id.chip_moon), Moon)
        initChips(view.findViewById(R.id.chip_mars), Mars)
        val rg = view.findViewById<ChipGroup>(R.id.chipGroup)
       // (rg.getChildAt(getCodeStyle(MyCoolCodeStyle))).setChecked(true)
    }

    private fun initChips(chip: Chip, myCoolCodeStyle: Int) {
        chip.setOnClickListener {
            val act = activity as MainActivity
            act.theme(myCoolCodeStyle)
        }
    }

    companion object {
        fun newInstance() = ChipsFragment()
    }
}