package com.example.jakartapostdicoding.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakartapostdicoding.R
import com.example.jakartapostdicoding.core.data.source.Resource
import com.example.jakartapostdicoding.core.ui.NewsTechAdapter
import com.example.jakartapostdicoding.core.ui.ViewModelFactory
import com.example.jakartapostdicoding.databinding.FragmentHomeBinding
import com.example.jakartapostdicoding.detail.DetailActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){

            val newsTechAdapter = NewsTechAdapter()
            newsTechAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

            homeViewModel.newsTech.observe(viewLifecycleOwner) { newsTech ->
                if (newsTech != null) {
                    when (newsTech) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsTechAdapter.setData(newsTech.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = newsTech.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvNews) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsTechAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}