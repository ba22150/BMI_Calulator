package com.example.bmi_calulator
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE
import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.bmi_calulator.UserDBHelper.Companion.DB_NAME
import com.example.bmi_calulator.UserDBHelper.Companion.DB_VERSION

class BmiDBHelper (context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
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

    


    companion object {
        val DB_VERSION = 1
        val DB_NAME = "BMIResult.db"

        private val SQL_CREATE_BMI =
            "CREATE TABLE " + DBDetails.BMIInfo.BMITABLE + " (" +
                    DBDetails.BMIInfo.COLUMN_DATE + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_EMAIL + "  TEXT PRIMARY KEY," +
                    DBDetails.BMIInfo.COLUMN_HEIGHT + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_WEIGHT + " TEXT," +
                    DBDetails.BMIInfo.COLUMN_BMI + " TEXT)"
        private val TABLE_ENTRY ="DROP TABLE IF EXISTS " + DBDetails.BMIInfo.BMITABLE   //to drop a table


    }
}