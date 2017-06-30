package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseSQLiteOpenHelper protected constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private val TAG = "[BaseSQLiteOpenHelper]"
    }

    protected abstract val createTableSqlsOnCreate: Array<String>

    protected abstract val dropTableNamesOnUpgrade: Array<String>

    override fun onCreate(db: SQLiteDatabase) {
        val sqls = createTableSqlsOnCreate
        for (sql in sqls) {
            db.execSQL(sql)
            println(TAG + " execSQL: " + sql)
        }
    }

    /**
     * This will be called when you change version number.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        println("$TAG Database going to upgrades from Version $oldVersion to Version $newVersion")
        val names = dropTableNamesOnUpgrade
        for (name in names) {
            db.execSQL("drop table if exists " + name)
            println(TAG + " Drop table: " + name)
        }

        onCreate(db)
    }

}
