package com.example.bmi_calulator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BMICalculator : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_calculate)
        val button : Button = findViewById(R.id.BMIButton)

        button.setOnClickListener {
            val heightE : EditText = findViewById(R.id.height)
            val weightE : EditText =findViewById(R.id.weight)
            val he = heightE.text .toString()
            val we = weightE.text .toString()
            if (he==("")){
                showToast("Please enter height")
            }
            else if (we.equals("")){
                showToast("Please enter weight")
            }
            else {
            doThisWhenButtonClicked()
            }
        }

        val preBmi : Button = findViewById(R.id.previousBMI)
        preBmi.setOnClickListener {
            val intent : Intent = Intent (this , history::class.java)
            startActivity(intent)
        }

        val logout: Button = findViewById(R.id.logoutBMI)
        logout.setOnClickListener {

            finish()


        }

    }

    fun doThisWhenButtonClicked() {

        val heightE : EditText = findViewById(R.id.height)
        val weightE : EditText =findViewById(R.id.weight)
        val he = heightE.text .toString()
        val we = weightE.text .toString()
        val resultBMI : TextView = findViewById(R.id.BMIResult)
        val rStatement : TextView = findViewById(R.id.ResultStatement)
        val bmi :Float= calculateBMI( he.toFloat() ,we.toFloat())

        val viewGroup: ViewGroup = findViewById(android.R.id.content)
        val view: View = viewGroup.getRootView()

        hideSoftKeyboard(view);


        resultBMI.text = bmi.toString()

        if (bmi<18.5) {
            rStatement.text = getString(R.string.underweight)
        }
        else if (18.5<bmi && bmi <24.9) {
            rStatement.text = getString(R.string.weight_perfect)
        }
        else if (25<bmi && bmi<29.9){
            rStatement.text= getString(R.string.result_overweight)
        }
        else {
            rStatement.text =getString(R.string.obese)
        }




    }

    fun calculateBMI (height : Float , weight : Float) : Float{

        val cmh : Float = height
        val w : Float = weight
        val meterH : Float =  cmh/100
        return (w/(meterH*meterH))

    }
    fun showToast (t:String) {
        val st: Toast = Toast.makeText(applicationContext, t, Toast.LENGTH_SHORT)
        st.show()
    }
    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}