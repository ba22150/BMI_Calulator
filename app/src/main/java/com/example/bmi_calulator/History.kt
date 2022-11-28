package com.example.bmi_calulator
import android.os.Bundle
import android.app.Activity
import android.icu.util.LocaleData
import android.view.Gravity
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

class History :Activity() {
    lateinit var bmiDBHelper: BmiDBHelper   // declaring the variable to get the function in class BmiDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)
        bmiDBHelper= BmiDBHelper(this)  // initialising the variable

        val bmiRecord : ArrayList<Bmi> = bmiDBHelper.readBMI(WhoLoggedIn.userMail)    // assigning the read BMI record to the variable bmiRecord which is of Arraylist type
        val verticalLinearLayout : LinearLayout = findViewById(R.id.verticalLinearlayout)  //referring the linear layout 
        val parameters: LayoutParams =
            LinearLayout.LayoutParams(  // variable which will have the height and width attribute of the  new linear layout
                LinearLayout.LayoutParams.MATCH_PARENT,  // for height
                LinearLayout.LayoutParams.MATCH_PARENT   // for width
            )
        val textParameters: LayoutParams =
            LinearLayout.LayoutParams(  // providing parameters to textView
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1F
            )


        for(x in 0..bmiRecord.size - 1) {
            val newLinearLayout: LinearLayout = LinearLayout(this)  // declaring a new linear layout and initializing it

            var bmi : Bmi = bmiRecord.get(x)   // assigning the current BMI record to the variable

            newLinearLayout.layoutParams = parameters // this will contain the height and the width of the newLinear layout
            newLinearLayout.orientation = LinearLayout.HORIZONTAL  // providing Horizontal orientation to newlinear layout

            for (i in 0..3) {

                val textView: TextView = TextView(this)
                if (i==0){
                    var date : Date = bmi.date    // will take the date parameter of the bmi which have the BMI record content
                    val dateFormatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault()) // defining the format of date

                    textView.text= dateFormatter.format(date)
                }
                if (i==1){
                    textView.text = bmi.height.toString()       // will take the height parameter of the bmi which have the BMI record content
                }
                if (i==2){
                    textView.text = bmi.weight.toString()      // will take the weight parameter of the bmi which have the BMI record content
                }
                if (i==3){
                    textView.text = String.format("%.2f", bmi.bmi)   // will take the bmi parameter of the bmi which have the BMI record content
                }


                textView.layoutParams = textParameters  // assigning the parameters which we assigned to textParameters to textView
//                textView.gravity = Gravity.CENTER

                newLinearLayout.addView(textView) // adding child view

            }
            verticalLinearLayout.addView(newLinearLayout) // adding the horizontal linear layout to main vertical linear layout
        }


    }
}