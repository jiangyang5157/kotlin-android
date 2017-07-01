package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteDatabase

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseSqliteApi protected constructor(sqliteOpenHelper: BaseSqliteOpenHelper) {

    private var db: SQLiteDatabase? = null

    private var dbOpenHelper = sqliteOpenHelper

    /**
     * call open() before any sqlite operation
     */
    protected fun open() {
        try {
            db = dbOpenHelper.getWritableDatabase()
        } catch (e: SQLiteException) {
            db = dbOpenHelper.getReadableDatabase()
        }
    }

    protected fun close() {
        dbOpenHelper.close()
    }

    /**
     * beginTransaction() before call execSQL
     */
    protected fun startTransaction() {
        db!!.beginTransaction()
    }

    protected fun finishTransaction() {
        try {
            db!!.setTransactionSuccessful()
        } finally {
            db!!.endTransaction()
        }
    }

    /**
     * call execSQLs
     */
    protected fun execSQLs(sqls: Array<String>) {
        db!!.beginTransaction()
        for (sql in sqls) {
            db!!.execSQL(sql)
        }
        db!!.setTransactionSuccessful()
    }

    /**
     * call execSQL
     */
    protected fun execSQL(sql: String) {
        db!!.execSQL(sql)
    }

    /**
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    protected fun insert(tableName: String, cv: ContentValues): Long {
        return db!!.insert(tableName, null, cv)
    }

    /**
     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun update(tableName: String, rowId: String, cv: ContentValues): Int {
        return db!!.update(tableName, cv, BaseTable.KEY_ROWID + " = ?", arrayOf(rowId))
    }

    protected fun query(tableName: String, col: Array<String>, orderBy: String): Cursor {
        val ret = db!!.query(tableName, col, null, null, null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * key equals value
     */
    protected fun queryValue(tableName: String, col: Array<String>, key: String, value: String, orderBy: String): Cursor {
        val ret = db!!.query(tableName, col, key + " = ?", arrayOf(value), null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * key contains like
     */
    protected fun queryLike(tableName: String, col: Array<String>, key: String, like: String, orderBy: String): Cursor {
        val ret = db!!.query(tableName, col, key + " like ?", arrayOf("%$like%"), null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun delete(tableName: String): Int {
        return db!!.delete(tableName, null, null)
    }

    /**
     * by key equals value

     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun delete(tableName: String, key: String, value: String): Int {
        return db!!.delete(tableName, key + " = ?", arrayOf(value))
    }

    companion object {

        /**
         * orderBy means:"cast (" + key + " as integer)" + orderBy
         *
         *
         * Example usage for params orderBy: Table.KEY + " " + ORDER_BY_DESC
         *
         */
        private val ORDER_BY_DESC = "desc"
        private val ORDER_BY_ASC = "asc"

        fun buildOrderByDesc(key: String): String {
            return buildOrderBy(key, ORDER_BY_DESC)
        }

        fun buildOrderByAsc(key: String): String {
            return buildOrderBy(key, ORDER_BY_ASC)
        }

        private fun buildOrderBy(key: String, orderBy: String): String {
            return key + " " + orderBy
        }
    }

}