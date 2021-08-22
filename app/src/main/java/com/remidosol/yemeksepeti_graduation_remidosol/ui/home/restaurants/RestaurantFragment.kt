package com.remidosol.yemeksepeti_graduation_remidosol.ui.home.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.RestaurantFragmentBinding
import com.remidosol.yemeksepeti_graduation_remidosol.utils.adapters.RestaurantRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantFragment : Fragment() {

    @Inject
    private lateinit var sharedPref: SharedPreferencesManager

    private lateinit var _binding: RestaurantFragmentBinding

    private lateinit var viewModel: RestaurantViewModel

    private lateinit var adapter: RestaurantRecyclerViewAdapter

    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantFragmentBinding.inflate(inflater, container, false)
        sharedPref = SharedPreferencesManager(_binding.root.context)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        page = sharedPref.getPage()
        getRestaurants(page)
    }

    override fun onPause() {
        super.onPause()
        sharedPref.savePage(page)
    }

    private fun fetchDataWithScroll(page: Int = 1) {
        viewModel.listRestaurants(page = page).observe(viewLifecycleOwner, Observer {
            adapter.insertRestaurantData(it.data!!.data)
        })
    }

    private fun getRestaurants(page: Int) {
        sharedPref.savePage(page)
        viewModel.listRestaurants(page).observe(viewLifecycleOwner, Observer {
            adapter.setRestaurantsData(it.data!!.data)
        })
    }


    private fun initViews(view: View) {
        val recyclerView: RecyclerView = _binding.root.findViewById(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(_binding.root.context)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    sharedPref.savePage(page)
                    fetchDataWithScroll(page)
                }
            }
        })

        adapter = RestaurantRecyclerViewAdapter(_binding.root.context)
        recyclerView.adapter = adapter

    }

}