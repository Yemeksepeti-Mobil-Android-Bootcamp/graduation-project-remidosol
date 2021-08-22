package com.remidosol.yemeksepeti_graduation_remidosol.utils.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.LocalDataSource
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager
import javax.inject.Inject

class FoodRecyclerViewAdapter(
    private val foodList: List<Food>,
    private val mContext: Context
) : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodListViewHolder>() {

    @Inject
    private val localDataSource: LocalDataSource =
        LocalDataSource(SharedPreferencesManager(mContext))

    inner class FoodListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clickCount = 0

        private val foodImage: ImageView = itemView.findViewById(R.id.foodImageView)
        private val foodName: TextView = itemView.findViewById(R.id.foodNameTextView)
        private val foodDescription: TextView = itemView.findViewById(R.id.foodDescriptionTextView)
        private val foodPrice: TextView = itemView.findViewById(R.id.foodPriceTextView)
        val addToCartButton: AppCompatButton = itemView.findViewById(R.id.addToCartButton)

        private val circularProgressDrawable = CircularProgressDrawable(mContext)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(food: Food) {
            Glide.with(mContext)
                .load(food.imageUrl)
                .placeholder(circularProgressDrawable)
                .into(foodImage)

            foodName.text = food.name
            foodDescription.text = food.description
            foodPrice.text = food.price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val foodItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_list_item, parent, false)

        return FoodListViewHolder(foodItemView)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        val food: Food = foodList[position]

        holder.bind(food)

        fun increaseClickCount() {
            holder.clickCount++
        }

        fun isAdded(): Boolean {
            return holder.clickCount % 2 == 1
        }

        holder.addToCartButton.setOnClickListener {
            increaseClickCount()
            if (isAdded()) {

                holder.addToCartButton.background =
                    AppCompatResources.getDrawable(mContext, R.drawable.added_to_cart_button)
                holder.addToCartButton.text = mContext.getString(R.string.added)
                holder.addToCartButton.setTextColor(mContext.resources.getColor(R.color.white))

                val drawable = AppCompatResources.getDrawable(
                    mContext,
                    R.drawable.ic_done_24
                )
                val arrangedDrawable = DrawableCompat.wrap(drawable!!)
                DrawableCompat.setTint(arrangedDrawable, mContext.getColor(R.color.white))

                holder.addToCartButton.setCompoundDrawables(
                    null,
                    null,
                    drawable,
                    null
                )

                localDataSource.addToCart(
                    Food(
                        food.id,
                        food.restaurantId,
                        food.name,
                        food.description,
                        food.price,
                        food.imageUrl
                    )
                )
            } else {
                holder.addToCartButton.background =
                    AppCompatResources.getDrawable(mContext, R.drawable.add_to_cart_button)
                holder.addToCartButton.text = mContext.getString(R.string.add)
                holder.addToCartButton.setTextColor(mContext.resources.getColor(R.color.primary))

                val drawable = AppCompatResources.getDrawable(
                    mContext,
                    R.drawable.ic_add_24
                )
                val arrangedDrawable = DrawableCompat.wrap(drawable!!)
                DrawableCompat.setTint(arrangedDrawable, mContext.getColor(R.color.primary))

                holder.addToCartButton.setCompoundDrawables(
                    null,
                    null,
                    drawable,
                    null
                )

                localDataSource.removeItemFromCart(
                    Food(
                        food.id,
                        food.restaurantId,
                        food.name,
                        food.description,
                        food.price,
                        food.imageUrl
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = foodList.size
}