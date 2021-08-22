package com.remidosol.yemeksepeti_graduation_remidosol.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding.fragments.BeTogetherFragment
import com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding.fragments.EasyPaymentFragment
import com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding.fragments.EmptyFragment
import com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding.fragments.TastyDishFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    companion object {
        private const val FRAGMENT_COUNT = 3
    }

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BeTogetherFragment()
            1 -> TastyDishFragment()
            2 -> EasyPaymentFragment()
            else -> EmptyFragment()
        }
    }
}