package com.example.jakartapostdicoding.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.domain.model.NewsTech
import com.example.jakartapostdicoding.R
import com.example.jakartapostdicoding.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailNewsTech = intent.getParcelableExtra<NewsTech>(EXTRA_DATA)
        showDetailNewsTech(detailNewsTech)
    }

    private fun showDetailNewsTech(detailNewsTech: NewsTech?){
        detailNewsTech?.let {
            supportActionBar?.title = detailNewsTech.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            binding.content.tvDateDetail.text = detailNewsTech.pusblisedAt
            binding.content.tvDetailTitle.text = detailNewsTech.title
            binding.content.tvDetailDescription.text = detailNewsTech.headline
            Glide.with(this@DetailActivity)
                .load(detailNewsTech.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailNewsTech.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteNewsTech(detailNewsTech, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}