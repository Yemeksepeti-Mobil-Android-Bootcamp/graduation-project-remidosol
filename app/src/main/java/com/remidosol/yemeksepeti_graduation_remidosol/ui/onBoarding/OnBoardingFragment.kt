package com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.FragmentOnBoardingBinding
import com.remidosol.yemeksepeti_graduation_remidosol.di.DatabaseModule
import com.remidosol.yemeksepeti_graduation_remidosol.utils.ViewPagerAdapter
import com.remidosol.yemeksepeti_graduation_remidosol.utils.transformers.CubeInDepthPageTransformer
import javax.inject.Inject

class OnBoardingFragment : Fragment() {

    @Inject
    lateinit var databaseModule: DatabaseModule

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        initViewPager()
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        val skipTextView: TextView = binding.root.findViewById(R.id.skipText)
        val sharedPref = databaseModule.sharedPrefManager(binding.root.context)
        val token = sharedPref.getToken()
        skipTextView.setOnClickListener {
            if (!token.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
            }
        }
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.setPageTransformer(CubeInDepthPageTransformer())
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.setViewPager2(binding.viewPager)
    }
}