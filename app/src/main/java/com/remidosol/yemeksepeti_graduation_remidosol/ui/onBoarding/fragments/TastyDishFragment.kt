package com.remidosol.yemeksepeti_graduation_remidosol.ui.onBoarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remidosol.yemeksepeti_graduation_remidosol.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TastyDishFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_be_together, container, false)
    }
}