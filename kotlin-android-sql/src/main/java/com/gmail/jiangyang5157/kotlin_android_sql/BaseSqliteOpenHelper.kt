package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseSqliteOpenHelper protected constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    object Tag {
        val LOG: String = "BaseSqliteOpenHelper"
    }

    protected abstract val sqlsTableOnCreate: Array<String>

    protected abstract val tableNamesOnUpgrade: Array<String>

    override fun onCreate(db: SQLiteDatabase) {
        val sqls = sqlsTableOnCreate
        for (sql in sqls) {
            db.execSQL(sql)
            println("${Tag.LOG} execSQL: " + sql)
        }
    }

    /**
     * This will be called when you change version number.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        println("${Tag.LOG} Database upgrade from version $oldVersion to version $newVersion")
        val names = tableNamesOnUpgrade
        for (name in names) {
            db.execSQL("drop table if exists " + name)
            println("${Tag.LOG} drop table: " + name)
        }

        onCreate(db)
    }

}
