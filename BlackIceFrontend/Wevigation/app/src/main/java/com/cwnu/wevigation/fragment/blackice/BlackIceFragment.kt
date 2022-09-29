package com.cwnu.wevigation.fragment.blackice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cwnu.wevigation.databinding.FragmentBlackiceBinding

class BlackIceFragment: Fragment() {

    private lateinit var binding: FragmentBlackiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlackiceBinding.inflate(layoutInflater)

        return binding.root
    }

}