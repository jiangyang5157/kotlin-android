package com.gmail.jiangyang5157.kotlin_android_kit.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseDbOpenHelper protected constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        const val TAG = "BaseDbOpenHelper"
    }

    protected abstract val sqlsTableOnCreate: Array<String>

    protected abstract val tableNamesOnUpgrade: Array<String>

    override fun onCreate(db: SQLiteDatabase) {
        val sqls = sqlsTableOnCreate
        for (sql in sqls) {
            db.execSQL(sql)
            Log.d(TAG, "Execute SQL: $sql")
        }
    }

    /**
     * This will be called when you change version number.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "Database upgrade from version $oldVersion to version $newVersion")
        val names = tableNamesOnUpgrade
        for (name in names) {
            db.execSQL("drop table if exists $name")
            Log.d(TAG, "Drop table: $name")
        }

        onCreate(db)
    }

}
