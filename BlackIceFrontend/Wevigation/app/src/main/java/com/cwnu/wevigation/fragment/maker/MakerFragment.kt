package com.cwnu.wevigation.fragment.maker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.cwnu.wevigation.R
import com.cwnu.wevigation.databinding.FragmentMakerBinding

class MakerFragment: DialogFragment() {

    private lateinit var makerBinding: FragmentMakerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMakerBinding.inflate(inflater, container, false)
        makerBinding = binding

        val button: Button = makerBinding.root.findViewById(R.id.button)
        button.setOnClickListener {
            dismiss()
        }

        return makerBinding.root
    }


}