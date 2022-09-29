package com.cwnu.wevigation.fragment.exit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.cwnu.wevigation.R
import com.cwnu.wevigation.databinding.FragmentExitBinding

class ExitFragment: DialogFragment() {

    private lateinit var exitBinding: FragmentExitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentExitBinding.inflate(inflater, container, false)
        exitBinding = binding

        val buttonYes: Button = exitBinding.root.findViewById(R.id.buttonYes)
        buttonYes.setOnClickListener {
            activity?.finish()
        }

        val buttonNo: Button = exitBinding.root.findViewById(R.id.buttonNo)
        buttonNo.setOnClickListener {
            dismiss()
        }

        return exitBinding.root
    }

}