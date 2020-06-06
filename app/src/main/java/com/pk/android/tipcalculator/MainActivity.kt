package com.pk.android.tipcalculator

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.progress = INITIAL_TIP_PERCENT
        txtpercent.text = "$INITIAL_TIP_PERCENT%"
        TipDiscription(INITIAL_TIP_PERCENT)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar:  SeekBar?, progress: Int, fromUser: Boolean) {
                txtpercent.text ="$progress"
                TipDiscription(progress)
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

    private fun TipDiscription(progress: Int) {
        val tipDiscription : String
        val tipPercent = seekBar.progress
        when (tipPercent){
            in 0..9 -> tipDiscription = "Poor"
            in 10..14 -> tipDiscription ="Acceptable"
            in 15..19 -> tipDiscription = "Good"
            in 20..24 -> tipDiscription = "Great"
            else -> tipDiscription = "Amazing"
        }
        txtTipDescription.text = tipDiscription.toString()
        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat()/seekBar.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
                ContextCompat.getColor(this, R.color.colorBestTip)
        ) as Int
        txtTipDescription.setTextColor(color)
    }

    private fun calculateTip() {
        //Get the value of the base and tip percent
        if (edTxtBase.text.toString().isEmpty()){
            txtTipAmount.text =""
            txtAmount.text = ""
            return
        }
        val baseAmount = edTxtBase.text.toString().toDouble()
        val tipPercent = seekBar.progress
        val tipAmount = baseAmount * tipPercent / 100
        txtTipAmount.text = tipAmount.toString()
        val  totalAmount = baseAmount + tipAmount
        txtAmount.text = totalAmount.toString()
    }
}