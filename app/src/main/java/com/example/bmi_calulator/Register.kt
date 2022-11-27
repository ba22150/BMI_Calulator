package com.example.bmi_calulator
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : Activity() {

    lateinit var userDBHelper: UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)
        userDBHelper= UserDBHelper(this)

        val signIn: Button = findViewById(R.id.signinR)

        signIn.setOnClickListener {
            val userR : EditText = findViewById(R.id.usernameR)
            val emailR : EditText =findViewById(R.id.emailR)
            val pwdR : EditText =findViewById(R.id.passwordR)
            val retypePwd : EditText =findViewById(R.id.retypePasswordR)
            val ur : String = userR.text.toString()
            val mail: String= emailR.text.toString()
            val pwd : String = pwdR.text.toString()
            val rpwd : String = retypePwd.text.toString()

            if (ur.equals("")){
//                showToast("Please enter an username", false)
            }

            else if (mail.equals("")){
                showToast("Please enter an email" ,false)
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                showToast("Invalid Email ,Please try again" ,false)
            }
            else if (pwd.equals("")){
                showToast("Please enter a password" ,false)

            }
            else if (rpwd.equals("")){
                showToast("please re-enter password" , false)

            }
            else if (!pwd.equals(rpwd)){
                showToast("password not same ,Please try again" ,false)
            }
            else if (!isValidPassword(pwd))
            {
                showToast("Invalid password " ,false)
                showToast("Password should be at least 6 characters long and have at least one number and one special character" ,true)
            }
            else {

                val user1: User = User(ur, mail, pwd)

                val user2: User= userDBHelper.readUser("bibin@gmail.com")
                val ifAdded: Boolean=userDBHelper.addUser(user1)
                val user3: User= userDBHelper.readUser("bibin@gmail.com")


                if (!ifAdded) {
                    showToast(" $mail already registered ,Please try again" , false)
                }
                else {
                    finish()

                    val intent: Intent = Intent(this, BMICalculator::class.java)
                    startActivity(intent)

                }





            }

        }





    }

    fun showToast (t:String , isLong: Boolean){
        var toastLength = Toast.LENGTH_SHORT
        if(isLong){
            toastLength = Toast.LENGTH_LONG
        }
        val st: Toast = Toast.makeText(applicationContext ,t ,toastLength)
        st.show()
    }
    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[-!_£*@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }
}
