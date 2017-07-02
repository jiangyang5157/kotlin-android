package com.gmail.jiangyang5157.kotlin_android_sql

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import android.content.ContentValues
import android.database.Cursor
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Yang Jiang on July 01, 2017
 */
@RunWith(AndroidJUnit4::class)
class BaseSqliteApiTest {

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertNotEquals(-1, TestSqliteApi.getInstance(appContext).insertTestTable("1st data"))
        assertNotEquals(-1, TestSqliteApi.getInstance(appContext).insertTestTable("2nd special"))
        assertNotEquals(-1, TestSqliteApi.getInstance(appContext).insertTestTable("3rd data"))
        assertNotEquals(-1, TestSqliteApi.getInstance(appContext).insertTestTable("4th data"))
        val cursor = TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(4, cursor.count)
    }

    @After
    fun tearDown() {
        val appContext = InstrumentationRegistry.getTargetContext()
        TestSqliteApi.getInstance(appContext).deleteTestTable()
        val cursor = TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(0, cursor.count)
    }

    @Test
    fun test_insert() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertNotEquals(-1, TestSqliteApi.getInstance(appContext).insertTestTable("5th data"))
    }

    @Test
    fun test_update() {
        val appContext = InstrumentationRegistry.getTargetContext()

        val before = TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        (1..before.count).map {
            val rowIdBefore = before.getLong(before.getColumnIndexOrThrow(TestTable.Column.KEY_ROWID))
            val dataBefore = before.getString(before.getColumnIndexOrThrow(TestTable.Column.KEY_DATA))
            println("test_update before [$rowIdBefore : $dataBefore]")
            before.moveToNext()
        }

        val cursor = TestSqliteApi.getInstance(appContext).queryTestTableByKey(TestTable.Column.KEY_DATA, "4th data", BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        val rowId = cursor.getLong(cursor.getColumnIndexOrThrow(TestTable.Column.KEY_ROWID))
        val updateTestTableResult = TestSqliteApi.getInstance(appContext).updateTestTable(rowId.toString(), "4th modified")
        assertTrue(updateTestTableResult > 0)

        val after = TestSqliteApi.getInstance(appContext).queryTestTable(BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        (1..after.count).map {
            val rowIdAfter = after.getLong(after.getColumnIndexOrThrow(TestTable.Column.KEY_ROWID))
            val dataAfter = after.getString(after.getColumnIndexOrThrow(TestTable.Column.KEY_DATA))
            println("test_update after [$rowIdAfter : $dataAfter]")
            after.moveToNext()
        }
    }

    @Test
    fun test_queryTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val cursor = TestSqliteApi.getInstance(appContext).queryTestTableByKey(TestTable.Column.KEY_DATA, "3rd data", BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(1, cursor.count)
    }

    @Test
    fun test_queryLikeTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val cursor = TestSqliteApi.getInstance(appContext).queryLikeTestTableByKey(TestTable.Column.KEY_DATA, "data", BaseSqliteApi.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(3, cursor.count)
    }

    @Test
    fun test_deleteTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val deleteTestTableByKeyResult = TestSqliteApi.getInstance(appContext).deleteTestTableByKey(TestTable.Column.KEY_DATA, "3rd data")
        assertTrue(deleteTestTableByKeyResult > 0)
    }

}

class TestTable {
    companion object {
        val TABLE_NAME: String = "TestTable"
        val SQL_TABLE_CREATION: String = "create table " +
                "$TABLE_NAME(${Column.KEY_ROWID} integer primary key autoincrement, " +
                "${Column.KEY_DATA} text);"
    }

    object Column {
        val KEY_ROWID: String = "_id"
        val KEY_DATA: String = "data"
    }
}

class TestSqliteOpenHelper(context: Context) : BaseSqliteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    companion object {
        private val DB_FILE_NAME: String = "test.db"
        private val DB_VERSION: Int = 4
    }

    override val sqlsTableOnCreate: Array<String>
        get() = arrayOf(TestTable.SQL_TABLE_CREATION)

    override val tableNamesOnUpgrade: Array<String>
        get() = arrayOf(TestTable.TABLE_NAME)

}

class TestSqliteApi private constructor(sqliteOpenHelper: BaseSqliteOpenHelper) : BaseSqliteApi(sqliteOpenHelper) {
    companion object {
        private val TAG: String = "TestSqliteApi"
        private var instance: TestSqliteApi? = null

        fun getInstance(context: Context): TestSqliteApi {
            if (instance == null) {
                instance = TestSqliteApi(TestSqliteOpenHelper(context))
                println("$TAG instance created")
            }
            return instance!!
        }
    }

    fun insertTestTable(data: String): Long {
        open()
        try {
            val cv = ContentValues()
            cv.put(TestTable.Column.KEY_DATA, data)
            return insert(TestTable.TABLE_NAME, cv)
        } finally {
            close()
        }
    }

    fun updateTestTable(rowId: String, data: String): Int {
        open()
        try {
            val cv = ContentValues()
            cv.put(TestTable.Column.KEY_DATA, data)
            return update(TestTable.TABLE_NAME, TestTable.Column.KEY_ROWID, rowId, cv)
        } finally {
            close()
        }
    }

    fun queryTestTable(orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ROWID, TestTable.Column.KEY_DATA)
            return query(TestTable.TABLE_NAME, col, orderBy)
        } finally {
            close()
        }
    }

    fun queryTestTableByKey(key: String, value: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ROWID, TestTable.Column.KEY_DATA)
            return queryValue(TestTable.TABLE_NAME, col, key, value, orderBy)
        } finally {
            close()
        }
    }

    fun queryLikeTestTableByKey(key: String, like: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ROWID, TestTable.Column.KEY_DATA)
            return queryLike(TestTable.TABLE_NAME, col, key, like, orderBy)
        } finally {
            close()
        }
    }

    fun deleteTestTable(): Int {
        open()
        try {
            return delete(TestTable.TABLE_NAME)
        } finally {
            close()
        }
    }

    fun deleteTestTableByKey(key: String, value: String): Int {
        open()
        try {
            return delete(TestTable.TABLE_NAME, key, value)
        } finally {
            close()
        }
    }

}