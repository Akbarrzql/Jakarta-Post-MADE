package com.example.favorite.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.NewsTechAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.favorite.di.favoriteModule
import com.example.jakartapostdicoding.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {


    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        if (this != null){
            val newsTechAdapter = NewsTechAdapter()
            newsTechAdapter.onItemClick = { selectedData ->
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteNewsTech.observe(this@FavoriteActivity) { dataNewsTech ->
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
}