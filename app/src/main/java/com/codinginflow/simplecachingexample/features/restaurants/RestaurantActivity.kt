package com.codinginflow.simplecachingexample.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinginflow.simplecachingexample.R
import com.codinginflow.simplecachingexample.databinding.ActivityRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {

    private val viewModel: RestaurantViewModel by viewModels()
    private val binding by lazy {
        ActivityRestaurantBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
                addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
            }

            viewModel.restaurants.observe(this@RestaurantActivity) { restaurants ->
                restaurantAdapter.submitList(restaurants)
            }
        }
    }
}