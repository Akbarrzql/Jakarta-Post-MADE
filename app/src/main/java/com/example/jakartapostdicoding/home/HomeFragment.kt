package com.example.jakartapostdicoding.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.NewsTechAdapter
import com.example.jakartapostdicoding.R
import com.example.jakartapostdicoding.databinding.FragmentHomeBinding
import com.example.jakartapostdicoding.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
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

            homeViewModel.newsTech.observe(viewLifecycleOwner) { newsTech ->
                if (newsTech != null) {
                    when (newsTech) {
                        is com.example.core.source.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is com.example.core.source.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsTechAdapter.setData(newsTech.data)
                        }

                        is com.example.core.source.Resource.Error -> {
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