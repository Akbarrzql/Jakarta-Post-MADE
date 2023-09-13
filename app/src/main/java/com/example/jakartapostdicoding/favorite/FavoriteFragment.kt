package com.example.jakartapostdicoding.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakartapostdicoding.R
import com.example.jakartapostdicoding.core.ui.NewsTechAdapter
import com.example.jakartapostdicoding.core.ui.ViewModelFactory
import com.example.jakartapostdicoding.databinding.FragmentFavoriteBinding
import com.example.jakartapostdicoding.detail.DetailActivity

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val newsTechAdapter = NewsTechAdapter()
            newsTechAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteNewsTech.observe(viewLifecycleOwner) { dataNewsTech ->
                newsTechAdapter.setData(dataNewsTech)
                binding.viewEmpty.root.visibility = if (dataNewsTech.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvNews){
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