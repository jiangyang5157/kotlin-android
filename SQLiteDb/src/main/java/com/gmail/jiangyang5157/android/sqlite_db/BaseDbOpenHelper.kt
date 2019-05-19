package com.gmail.jiangyang5157.android.sqlite_db

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseDbOpenHelper : SQLiteOpenHelper {

    companion object {

        const val TAG = "BaseDbOpenHelper"
    }

    protected abstract val sqlsTableOnCreate: Array<String>

    protected abstract val tableNamesOnUpgrade: Array<String>

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
            : super(context, name, factory, version)

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?)
            : super(context, name, factory, version, errorHandler)

    @RequiresApi(Build.VERSION_CODES.P)
    constructor(context: Context, name: String, version: Int, openParams: SQLiteDatabase.OpenParams)
            : super(context, name, version, openParams)

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
