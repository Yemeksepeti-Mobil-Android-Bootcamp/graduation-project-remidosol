package com.remidosol.yemeksepeti_graduation_remidosol.ui.home.restaurants.restaurantDetail

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Restaurant
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.RestaurantDetailFragmentBinding
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import com.remidosol.yemeksepeti_graduation_remidosol.utils.adapters.FoodRecyclerViewAdapter
import com.remidosol.yemeksepeti_graduation_remidosol.utils.adapters.RestaurantRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private lateinit var _binding: RestaurantDetailFragmentBinding

    private lateinit var viewModel: RestaurantDetailViewModel

    private lateinit var restaurant: Restaurant

    private lateinit var adapter: FoodRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantDetailFragmentBinding.inflate(inflater, container, false)

        restaurant = requireArguments().getParcelable("restaurant")!!

        getRestaurant()

        initViews(_binding.root, restaurant.restaurantFoods!!)

        return _binding.root
    }

    private fun initViews(view: View, restaurantFoods: List<Food>) {
        val recyclerView: RecyclerView = _binding.root.findViewById(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(_binding.root.context)

        adapter = FoodRecyclerViewAdapter(restaurantFoods, _binding.root.context)
        recyclerView.adapter = adapter
    }

    private fun getRestaurant() {

        viewModel.getRestaurant(restaurant.id).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    //
                }
                Resource.Status.SUCCESS -> {
                    Toast.makeText(
                        _binding.root.context,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    restaurant = it.data!!
                }
                Resource.Status.ERROR -> {
                    AlertDialog.Builder(_binding.root.context)
                        .setTitle("Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("Ok") { dialog, button ->
                            dialog.dismiss()
                        }.show()
                }
            }
        })
    }

}