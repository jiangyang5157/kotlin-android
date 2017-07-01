package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import android.content.ContentValues
import android.database.Cursor
import android.support.test.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Yang Jiang on July 01, 2017
 */
@RunWith(AndroidJUnit4::class)
class BaseSqliteApiTest {

    @Test
    fun test_BaseSqliteApi() {
        val appContext = InstrumentationRegistry.getTargetContext()
        println(TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.ORDER_BY_ASC).count)
        assert(true)
    }

    @Test
    fun test_BaseSqliteApi2() {
        val appContext = InstrumentationRegistry.getTargetContext()
        println(TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.ORDER_BY_ASC).count)
        assert(true)
    }
}

class TestTable : BaseTable() {
    companion object {
        val TABLE_NAME = "TestTable"

        val KEY_DATA = "data"

        val SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" + BaseTable.KEY_ROWID + " integer primary key autoincrement," + KEY_DATA + " text);"
    }
}

class TestSqliteOpenHelper(context: Context) : BaseSqliteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    companion object {
        private val DB_FILE_NAME = "test.db"
        private val DB_VERSION = 1
    }

    override val sqlsCreateTableOnCreate: Array<String>
        get() = arrayOf(TestTable.SQL_CREATE_TABLE)

    override val namesDropTableOnUpgrade: Array<String>
        get() = arrayOf(TestTable.TABLE_NAME)

}

class TestSqliteApi private constructor(sqliteOpenHelper: BaseSqliteOpenHelper) : BaseSqliteApi(sqliteOpenHelper) {
    companion object {
        private var instance : TestSqliteApi? = null

        fun  getInstance(context: Context): TestSqliteApi {
            if (instance == null)
                instance = TestSqliteApi(TestSqliteOpenHelper(context))

            return instance!!
        }
    }

    fun insertTestTable(data: String): Long {
        open()
        try {
            val cv = ContentValues()
            cv.put(TestTable.KEY_DATA, data)
            return insert(TestTable.TABLE_NAME, cv)
        } finally {
            close()
        }
    }

    fun updateTestTable(rowId: String, data: String): Int {
        open()
        try {
            val cv = ContentValues()
            cv.put(TestTable.KEY_DATA, data)
            return update(TestTable.TABLE_NAME, rowId, cv)
        } finally {
            close()
        }
    }

    fun queryTestTable(orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf<String>(BaseTable.KEY_ROWID, TestTable.KEY_DATA)
            return query(TestTable.TABLE_NAME, col, orderBy)
        } finally {
            close()
        }
    }

    fun queryTestTableByKey(key: String, value: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf<String>(BaseTable.KEY_ROWID, TestTable.KEY_DATA)
            return queryValue(TestTable.TABLE_NAME, col, key, value, orderBy)
        } finally {
            close()
        }
    }

    fun queryLikeTestTableByKey(key: String, like: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf<String>(BaseTable.KEY_ROWID, TestTable.KEY_DATA)
            return queryLike(TestTable.TABLE_NAME, col, key, like, orderBy)
        } finally {
            close()
        }
    }

    fun deleteTestTable(key: String, value: String): Int {
        open()
        try {
            return delete(TestTable.TABLE_NAME, key, value)
        } finally {
            close()
        }
    }

}