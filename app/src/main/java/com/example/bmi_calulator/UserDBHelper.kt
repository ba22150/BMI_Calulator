package com.example.bmi_calulator

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE
import android.provider.ContactsContract.CommonDataKinds.Email

class UserDBHelper(context: Context): SQLiteOpenHelper(context,DB_NAME,null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)  /* Function to create the table in the Database */
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(TABLE_ENTRY)  // Function to drop the existing table
        onCreate(db)  // Calling the  function to create the upgraded table
    }

    @kotlin.jvm.Throws(SQLiteConstraintException::class) //to throw exception
    fun addUser(user: User) : Boolean{     // Function adding details to user information table
        val db = writableDatabase
        val values= ContentValues()
        values.put(DBDetails.UserInfo.COLUMN_EMAIL, user.email)
        values.put(DBDetails.UserInfo.COLUMN_USER_NAME,user.name)
        values.put(DBDetails.UserInfo.COLUMN_PASSWORD,user.password)
        db.insert(DBDetails.UserInfo.TABLE, null,values)   //inserting the details to the table
        return true
    }

    @kotlin.jvm.Throws(SQLiteConstraintException::class)
    fun readUser(email: String): User {
        val user: User
        var name: String =""
        var password: String=""
        val cursor: Cursor?
        val db = writableDatabase
        cursor= db.rawQuery("select * from "+DBDetails.UserInfo.TABLE+" WHERE "+DBDetails.UserInfo.COLUMN_EMAIL+"='"+email+"'", null)
        if (cursor.moveToFirst()){
            val nameTemp=cursor.getColumnIndex(DBDetails.UserInfo.COLUMN_USER_NAME)
            if (nameTemp>=0){
                name=cursor.getString(nameTemp)
            }
            val passwordTemp=cursor.getColumnIndex(DBDetails.UserInfo.COLUMN_PASSWORD)
            if (passwordTemp>=0){
                password=cursor.getString(passwordTemp)
            }
        }
        cursor.close()
        user=User(name,email,password)
        return user
    }


    companion object {
        val DB_VERSION= 2
        val DB_NAME="BMIDatabase.db"

        private val SQL_CREATE_ENTRIES=
            "CREATE TABLE " + DBDetails.UserInfo.TABLE+ " (" +
                    DBDetails.UserInfo.COLUMN_EMAIL+ "TEXT PRIMARY KEY,"+
                    DBDetails.UserInfo.COLUMN_USER_NAME+ "TEXT," +
                    DBDetails.UserInfo.COLUMN_PASSWORD+"TEXT)"

        private val TABLE_ENTRY ="DROP TABLE IF EXISTS" + DBDetails.UserInfo.TABLE
    }
}