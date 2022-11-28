package com.example.bmi_calulator
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

class BmiDBHelper (context: Context): SQLiteOpenHelper(context, DB_NAME,null, DBDetails.UserInfo.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(BmiDBHelper.SQL_CREATE_BMI)  /* Function to create the table in the Database */
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(BmiDBHelper.TABLE_ENTRY)  // Function to drop the existing table
        onCreate(db)  // Calling the  function to create the upgraded table
    }

    fun addBMI (bmi: Bmi): Boolean{    // Function to add details to BMI table in BMI database
        val db = writableDatabase
        val values= ContentValues()
        values.put(DBDetails.BMIInfo.COLUMN_DATE, bmi.date.toString()) // as put does not accept date data type hence converted to string
        values.put(DBDetails.BMIInfo.COLUMN_EMAIL,bmi.email)
        values.put(DBDetails.BMIInfo.COLUMN_HEIGHT,bmi.height)
        values.put(DBDetails.BMIInfo.COLUMN_WEIGHT,bmi.weight)
        values.put(DBDetails.BMIInfo.COLUMN_BMI,bmi.bmi)

        db.insert(DBDetails.BMIInfo.BMITABLE, null,values)   //inserting the details to the table
        return true
    }

    fun readBMI (email: String): ArrayList<Bmi>{   // Function to read the BMI table
        var bmiList: ArrayList<Bmi> = ArrayList<Bmi>()  //declaring a list that will hold the contents of BMI table
        var date: Date = Date()
        var height : Float = 0F
        var weight : Float =0F
        var bmiResult : Float=0F
        val cursor: Cursor?
        val db = writableDatabase
        cursor= db.rawQuery("select * from "+DBDetails.BMIInfo.BMITABLE+" WHERE "+DBDetails.UserInfo.COLUMN_EMAIL+"='"+email+"'", null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {    // while loops is given to take the list of all bmi results if any
                val dateTemp = cursor.getColumnIndex(DBDetails.BMIInfo.COLUMN_DATE)
                val heightTemp = cursor.getColumnIndex(DBDetails.BMIInfo.COLUMN_HEIGHT)
                val weightTemp = cursor.getColumnIndex(DBDetails.BMIInfo.COLUMN_WEIGHT)
                val bmiResultTemp = cursor.getColumnIndex(DBDetails.BMIInfo.COLUMN_BMI)

                if (dateTemp >= 0) {
                    date = Date(cursor.getString(dateTemp))  //to convert date to "DATE" data type
                }
                if (heightTemp >= 0) {
                    height = cursor.getFloat(heightTemp)
                }
                if (weightTemp >= 0) {
                    weight = cursor.getFloat(weightTemp)
                }
                if (bmiResultTemp >= 0) {
                    bmiResult = cursor.getFloat(bmiResultTemp)
                }
                var resultBmi: Bmi= Bmi(date, email, height, weight, bmiResult)   //Declaring and initializing a variable to store BMI Record
                bmiList.add(resultBmi)  // adding the BMI record to BMI list one by one
                cursor.moveToNext()  // to move cursor to next index

            }
        }

        cursor.close()
        return bmiList


    }


    companion object {
        val DB_NAME = "BMIDatabase.db"

        private val SQL_CREATE_BMI =
            "CREATE TABLE " + DBDetails.BMIInfo.BMITABLE + " (" +
                    DBDetails.BMIInfo.COLUMN_DATE + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_EMAIL + "  TEXT," +
                    DBDetails.BMIInfo.COLUMN_HEIGHT + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_WEIGHT + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_BMI + " TEXT)"
        private val TABLE_ENTRY ="DROP TABLE IF EXISTS " + DBDetails.BMIInfo.BMITABLE   //to drop a table


    }
}