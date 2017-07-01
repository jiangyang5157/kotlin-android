package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before

import org.junit.runner.RunWith

/**
 * Created by Yang Jiang on July 01, 2017
 */
@RunWith(AndroidJUnit4::class)
class BaseSQLiteApiTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

}

class TestTable : BaseTable() {
    companion object {
        val TABLE_NAME = "Test"

        val KEY_DATA = "data"

        val SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" + BaseTable.KEY_ROWID + " integer primary key autoincrement," + KEY_DATA + " text);"
    }
}

class TestSQLiteOpenHelper(context: Context) : BaseSQLiteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    companion object {
        private val DB_FILE_NAME = "test.db"
        private val DB_VERSION = 1
    }

    override val sqlsCreateTableOnCreate: Array<String>
        get() = arrayOf(TestTable.SQL_CREATE_TABLE)

    override val namesDropTableOnUpgrade: Array<String>
        get() = arrayOf(TestTable.TABLE_NAME)

}

class TestSQLiteApi(context: Context) : BaseSQLiteApi(context) {

    override fun providerSQLiteOpenHelper(context: Context): BaseSQLiteOpenHelper {
        return TestSQLiteOpenHelper(context)
    }

}