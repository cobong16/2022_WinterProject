package com.cwnu.wevigation.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.cwnu.wevigation.R
import com.cwnu.wevigation.databinding.FragmentInfoBinding

class InfoFragment: DialogFragment() {

    private lateinit var infoBinding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInfoBinding.inflate(inflater, container, false)
        infoBinding = binding

        val button: Button = infoBinding.root.findViewById(R.id.button)
        button.setOnClickListener {
            dismiss()
        }

        return infoBinding.root
    }
}