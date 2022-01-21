package com.example.picture_of_the_day.picture_of_the_day


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.picture_of_the_day.R
import com.example.picture_of_the_day.databinding.FragmentMainStartBinding
import com.google.android.material.snackbar.Snackbar

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentMainStartBinding? = null
    private val binding get() = _binding!!
    private var isExpanded = false

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this)[PictureOfTheDayViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner, { renderData(it) })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainStartBinding.inflate(inflater, container, false)
        val im = binding.imageView
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                binding.progressBar.visibility=View.GONE
                binding.group.visibility=View.VISIBLE
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_no_photo_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
                serverResponseData.explanation?.let {
                    binding.text.typeface = Typeface.createFromAsset(activity?.assets, "Dongle-Regular.ttf")
                    binding.text.text=it
                }
            }
            is PictureOfTheDayData.Loading -> {
                binding.group.visibility=View.GONE
                binding.progressBar.visibility=View.VISIBLE
            }
            is PictureOfTheDayData.Error -> {
                binding.group.visibility=View.GONE
                binding.progressBar.visibility=View.GONE
                Snackbar
                    .make(binding.root, "Error: ошибка соединения", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getData() }
                    .show()
            }
        }
    }


    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}