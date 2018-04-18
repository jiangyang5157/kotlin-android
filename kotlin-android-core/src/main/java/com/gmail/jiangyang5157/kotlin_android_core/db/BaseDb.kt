package com.gmail.jiangyang5157.kotlin_android_core.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

/**
 * Created by Yang Jiang on July 01, 2017
 */
abstract class BaseDb protected constructor(private var mDbOpenHelper: BaseDbOpenHelper) {

    // cast (key as integer) `orderBy`
    object OrderBy {

        fun asc(key: String): String = buildOrderBy(key, "asc")

        fun desc(key: String): String = buildOrderBy(key, "desc")

        private fun buildOrderBy(key: String, orderBy: String): String = "$key $orderBy"
    }

    private lateinit var mDb: SQLiteDatabase

    /**
     * call open() before any sqlite operation
     */
    protected fun open() {
        mDb = try {
            mDbOpenHelper.writableDatabase
        } catch (e: SQLiteException) {
            mDbOpenHelper.readableDatabase
        }
    }

    protected fun close() = mDbOpenHelper.close()

    /**
     * beginTransaction() before call execSQL
     */
    protected fun startTransaction() {
        mDb.beginTransaction()
    }

    protected fun finishTransaction() {
        try {
            mDb.setTransactionSuccessful()
        } finally {
            mDb.endTransaction()
        }
    }

    /**
     * call execSQLs
     */
    protected fun execSQLs(sqls: Array<String>) {
        mDb.beginTransaction()
        for (sql in sqls) {
            mDb.execSQL(sql)
        }
        mDb.setTransactionSuccessful()
    }

    /**
     * call execSQL
     */
    protected fun execSQL(sql: String) {
        mDb.execSQL(sql)
    }

    /**
     * @return the row id of the newly inserted row, or -1 if an error occurred
     */
    protected fun insert(tableName: String, cv: ContentValues): Long {
        return mDb.insert(tableName, null, cv)
    }

    /**
     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun update(tableName: String, key: String, value: String, cv: ContentValues): Int {
        return mDb.update(tableName, cv, "$key = ?", arrayOf(value))
    }

    protected fun query(tableName: String, col: Array<String>, orderBy: String): Cursor {
        val ret = mDb.query(tableName, col, null, null, null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * key equals value
     */
    protected fun queryValue(tableName: String, col: Array<String>, key: String, value: String, orderBy: String): Cursor {
        val ret = mDb.query(tableName, col, "$key = ?", arrayOf(value), null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * key contains like
     */
    protected fun queryLike(tableName: String, col: Array<String>, key: String, like: String, orderBy: String): Cursor {
        val ret = mDb.query(tableName, col, "$key like ?", arrayOf("%$like%"), null, null, orderBy)
        ret?.moveToFirst()
        return ret
    }

    /**
     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun delete(tableName: String): Int {
        return mDb.delete(tableName, null, null)
    }

    /**
     * @return the number of rows affected, return value <= 0 means failed
     */
    protected fun delete(tableName: String, key: String, value: String): Int {
        return mDb.delete(tableName, "$key = ?", arrayOf(value))
    }

}