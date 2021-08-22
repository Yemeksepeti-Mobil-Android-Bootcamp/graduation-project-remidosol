package com.remidosol.yemeksepeti_graduation_remidosol.utils.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Restaurant

class RestaurantRecyclerViewAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantListViewHolder>() {

    var restaurantList = ArrayList<Restaurant>()

    inner class RestaurantListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val restaurantLogo: ImageView = itemView.findViewById(R.id.restaurantLogoImageView)
        private val restaurantName: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        private val restaurantRating: TextView = itemView.findViewById(R.id.ratingTextView)
        private val restaurantCategory: TextView = itemView.findViewById(R.id.categoryTextView)
        private val restaurantArrival: TextView = itemView.findViewById(R.id.arrivalTimeTextView)

        private val circularProgressDrawable = CircularProgressDrawable(mContext)

        fun bind(restaurant: Restaurant) {
            Glide.with(mContext)
                .load(restaurant.logoUrl)
                .placeholder(circularProgressDrawable)
                .into(restaurantLogo)

            restaurantName.text = restaurant.name.toString()
            restaurantRating.text = restaurant.rating.toString()
            restaurantCategory.text = restaurant.category
            restaurantArrival.text =
                mContext.resources.getString(R.string.min, restaurant.arrivalTime)

            itemView.setOnClickListener {
                val bundle: Bundle = bundleOf()
                bundle.putParcelable("restaurant", restaurant)
                it.findNavController()
                    .navigate(R.id.action_restaurantFragment_to_restaurantDetailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        val restaurantItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_item, parent, false)

        return RestaurantListViewHolder(restaurantItemView)
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        val restaurant: Restaurant = restaurantList[position]

        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setRestaurantsData(list: List<Restaurant>) {
        this.restaurantList = ArrayList(list)
        notifyDataSetChanged()
    }

    fun insertRestaurantData(list: List<Restaurant>) {
        val listIndex = this.restaurantList.size
        this.restaurantList.addAll(ArrayList(list))
        notifyItemInserted(listIndex)
    }

}