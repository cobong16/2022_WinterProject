package com.cwnu.wevigation.fragment.googleMaps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.cwnu.wevigation.R

class CheckBoxFragment: DialogFragment() {

    private lateinit var fContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //customDialogBinding = FragmentCustomDialogBinding.inflate(inflater, container, false)

        val rootView : View = inflater.inflate(R.layout.fragment_checkbox,container,false)

        val submitBtn: Button = rootView.findViewById(R.id.button1)

        val checkbox1: CheckBox = rootView.findViewById(R.id.checkbox1)
        val checkbox2: CheckBox = rootView.findViewById(R.id.checkbox2)
        val checkbox3: CheckBox = rootView.findViewById(R.id.checkbox3)

        val checkbox_rain1: CheckBox = rootView.findViewById(R.id.checkbox_rain1)
        val checkbox_rain2: CheckBox = rootView.findViewById(R.id.checkbox_rain2)

        submitBtn.setOnClickListener {

            var result: String = ""

            //checkbox_ice
            if (checkbox1.isChecked) {
                result += checkbox1.text.toString()
                result += ","
            }
            if (checkbox2.isChecked) {
                result += checkbox2.text.toString()
                result += ","
            }
            if (checkbox3.isChecked) {
                result += checkbox3.text.toString()
                result += ","
            }

            //checkbox_rain
            if (checkbox_rain1.isChecked) {
                result += checkbox_rain1.text.toString()
                result += ","
            }
            if (checkbox_rain2.isChecked) {
                result += checkbox_rain2.text.toString()
                result += ","
            }

            setFragmentResult("requestKey", bundleOf("bundleKey" to result))

            dismiss()
        }

        return rootView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }
}