package com.pk.android.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.progress = INITIAL_TIP_PERCENT
        txtpercent.text = "$INITIAL_TIP_PERCENT%"
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar:  SeekBar?, progress: Int, fromUser: Boolean) {
                txtpercent.text ="$progress"
                calculateTip()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        edTxtBase.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
              calculateTip()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    private fun calculateTip() {
        //Get the value of the base and tip percent
        val baseAmount = edTxtBase.text.toString().toDouble()
        val tipPercent = seekBar.progress
        val tipAmount = baseAmount * tipPercent / 100
        txtTipAmount.text = tipAmount.toString()
        val  totalAmount = baseAmount + tipAmount
        txtAmount.text = totalAmount.toString()
    }
}