package com.example.bmi_calulator

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


class LoginMain : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        val loginB: Button = findViewById(R.id.login)
        val signupB: Button = findViewById(R.id.signup)



        loginB.setOnClickListener {
            val userL: EditText = findViewById(R.id.usernameL)
            val pwdL: EditText = findViewById(R.id.passwordL)
            val user: String = userL.text.toString()
            val pass: String = pwdL.text.toString()

            if (user.equals("")){
                showToast("Please Enter Usernameeeee")
            }
            else if (pass.equals("")){
                showToast("Please Enter Password")
            }
            else {
                val loguser : String = RegController.loginUser (user,pass)
                if (loguser.equals("Wrong Password")){
                    showToast("Incorrect password .Please try again")
                }
                else if (loguser.equals("Unsuccessful")){
                    showToast("Invalid email ID")
                }
                else{
                    val intent: Intent = Intent(this, BMICalculator::class.java)
                    startActivity(intent)
                }
            }
        }
        signupB.setOnClickListener {
            val intent : Intent = Intent (this,Register::class.java)
            startActivity(intent)
        }
    }
    fun showToast (t:String) {
        val st: Toast = Toast.makeText(applicationContext, t, Toast.LENGTH_SHORT)
        st.show()
    }
}