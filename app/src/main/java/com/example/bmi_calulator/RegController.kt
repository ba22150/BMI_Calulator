package com.example.bmi_calulator
import android.widget.Toast

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


class RegController {
    companion object{
        var allUsers : ArrayList<User> = ArrayList<User>()

        fun addUser(user1 : User) {
            allUsers.add(user1)
        }

        fun getAllSavedUsers() : ArrayList<User>{
            return allUsers

        }

        fun regUser (regUser : User) : Boolean {




            return true

        }

        fun loginUser ( useremail: String , password : String) : String {
            val len1:Int= allUsers.size
            for (i in 0 ..len1-1){
                if (useremail.equals(allUsers.get(i).email)){
                    if (password.equals(allUsers.get(i).password)){
                        return "successful"
                    }
                    else{
                        return "Wrong Password"
                    }
                }
            }
            return "Unsuccessful"
            }
        }



}