package com.example.bmi_calulator

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginMain : Activity() {
    lateinit var userDBHelper: UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        val loginB: Button = findViewById(R.id.login)
        val signupB: Button = findViewById(R.id.signup)
        userDBHelper= UserDBHelper(this)  // initialising class to variable




        loginB.setOnClickListener {
            val userL: EditText = findViewById(R.id.usernameL)
            val pwdL: EditText = findViewById(R.id.passwordL)
            val email: String = userL.text.toString()
            val pass: String = pwdL.text.toString()


            if (email.equals("")){
                showToast("Please Enter Username")
            }
            else if (pass.equals("")){
                showToast("Please Enter Password")
            }
            else if (!userDBHelper.doesUseralreadyexist(email)){        //checking if email already in database
                showToast("Email not registered ,Please Sign Up")
            }

            else {

                val loguser : User = userDBHelper.readUser(email)
                if (!loguser.password.equals(pass)){                           //checking if the password is correct
                    showToast("Incorrect password,Please try again")
                }
                else{
                    val intent: Intent = Intent(this, BMICalculator::class.java)    // landing to next Page
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