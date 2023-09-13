package com.example.jakartapostdicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jakartapostdicoding.databinding.ActivityMainBinding
import com.example.jakartapostdicoding.favorite.FavoriteFragment
import com.example.jakartapostdicoding.home.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.favorite -> {
                    setCurrentFragment(FavoriteFragment())
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}