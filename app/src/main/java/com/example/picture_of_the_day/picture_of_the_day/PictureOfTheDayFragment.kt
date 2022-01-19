package com.example.picture_of_the_day.picture_of_the_day


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
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
                    Log.d("tag", it)
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

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}