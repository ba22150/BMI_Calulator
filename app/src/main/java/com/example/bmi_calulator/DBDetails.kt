package com.example.bmi_calulator

import android.provider.BaseColumns

/*This is a class which contains the details  of table contents */

class DBDetails {


    class UserInfo: BaseColumns {
        companion object{
            const val TABLE="userInformation"
            const val COLUMN_USER_NAME="name"
            const val COLUMN_EMAIL="email_id"
            const val COLUMN_PASSWORD="password"
        }
    }
    class BMIInfo: BaseColumns{
        companion object{
            const val BMITABLE="bmiTable"
            const val COLUMN_DATE="date"
            const val COLUMN_EMAIL="email_id"
            const val COLUMN_HEIGHT="user_height"
            const val COLUMN_WEIGHT="user_weight"
            const val COLUMN_BMI="bmi_Result"
        }
    }





}