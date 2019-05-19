package com.gmail.jiangyang5157.android.sqlite_db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Yang Jiang on July 01, 2017
 */
@RunWith(AndroidJUnit4::class)
class BaseDbTest {

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertNotEquals(-1, TestDb.getInstance(appContext).insertTestTable("1st data"))
        assertNotEquals(-1, TestDb.getInstance(appContext).insertTestTable("2nd special"))
        assertNotEquals(-1, TestDb.getInstance(appContext).insertTestTable("3rd data"))
        assertNotEquals(-1, TestDb.getInstance(appContext).insertTestTable("4th data"))
        val cursor = TestDb.getInstance(appContext).queryTestTable(BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(4, cursor.count)
    }

    @After
    fun tearDown() {
        val appContext = InstrumentationRegistry.getTargetContext()
        TestDb.getInstance(appContext).deleteTestTable()
        val cursor = TestDb.getInstance(appContext).queryTestTable(BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(0, cursor.count)
    }

    @Test
    fun test_insert() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertNotEquals(-1, TestDb.getInstance(appContext).insertTestTable("5th data"))
    }

    @Test
    fun test_update() {
        val appContext = InstrumentationRegistry.getTargetContext()

        val before = TestDb.getInstance(appContext).queryTestTable(BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        (1..before.count).map {
            val rowIdBefore = before.getLong(before.getColumnIndexOrThrow(TestTable.Column.KEY_ID))
            val dataBefore = before.getString(before.getColumnIndexOrThrow(TestTable.Column.KEY_DATA))
            println("test_update before [$rowIdBefore : $dataBefore]")
            before.moveToNext()
        }

        val cursor = TestDb.getInstance(appContext).queryTestTableByKey(TestTable.Column.KEY_DATA, "4th data", BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        val rowId = cursor.getLong(cursor.getColumnIndexOrThrow(TestTable.Column.KEY_ID))
        val updateTestTableResult = TestDb.getInstance(appContext).updateTestTable(rowId.toString(), "4th modified")
        assertTrue(updateTestTableResult > 0)

        val after = TestDb.getInstance(appContext).queryTestTable(BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        (1..after.count).map {
            val rowIdAfter = after.getLong(after.getColumnIndexOrThrow(TestTable.Column.KEY_ID))
            val dataAfter = after.getString(after.getColumnIndexOrThrow(TestTable.Column.KEY_DATA))
            println("test_update after [$rowIdAfter : $dataAfter]")
            after.moveToNext()
        }
    }

    @Test
    fun test_queryTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val cursor = TestDb.getInstance(appContext).queryTestTableByKey(TestTable.Column.KEY_DATA, "3rd data", BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(1, cursor.count)
    }

    @Test
    fun test_queryLikeTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val cursor = TestDb.getInstance(appContext).queryLikeTestTableByKey(TestTable.Column.KEY_DATA, "data", BaseDb.OrderBy.asc(TestTable.Column.KEY_DATA))
        assertEquals(3, cursor.count)
    }

    @Test
    fun test_deleteTestTableByKey() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val deleteTestTableByKeyResult = TestDb.getInstance(appContext).deleteTestTableByKey(TestTable.Column.KEY_DATA, "3rd data")
        assertTrue(deleteTestTableByKeyResult > 0)
    }

}

class TestTable {
    companion object {
        const val TABLE_NAME: String = "TestTable"
        const val SQL_TABLE_CREATION: String = "create table " + "$TABLE_NAME" +
                "(${Column.KEY_ID} integer primary key autoincrement, " +
                "${Column.KEY_DATA} text);"
    }

    object Column {
        const val KEY_ID: String = "_id"
        const val KEY_DATA: String = "data"
    }
}

class TestDbOpenHelper(context: Context) : BaseDbOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_FILE_NAME: String = "test.db"
        private const val DB_VERSION: Int = 4
    }

    override val sqlsTableOnCreate: Array<String>
        get() = arrayOf(TestTable.SQL_TABLE_CREATION)

    override val tableNamesOnUpgrade: Array<String>
        get() = arrayOf(TestTable.TABLE_NAME)

}

class TestDb private constructor(dbOpenHelper: BaseDbOpenHelper) : BaseDb(dbOpenHelper) {

    companion object {
        private const val TAG: String = "TestDb"
        private var INSTANCE: TestDb? = null

        fun getInstance(context: Context): TestDb {
            if (INSTANCE == null) {
                INSTANCE = TestDb(TestDbOpenHelper(context))
                println("$TAG INSTANCE created")
            }
            return INSTANCE!!
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
            return update(TestTable.TABLE_NAME, TestTable.Column.KEY_ID, rowId, cv)
        } finally {
            close()
        }
    }

    fun queryTestTable(orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ID, TestTable.Column.KEY_DATA)
            return query(TestTable.TABLE_NAME, col, orderBy)
        } finally {
            close()
        }
    }

    fun queryTestTableByKey(key: String, value: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ID, TestTable.Column.KEY_DATA)
            return queryValue(TestTable.TABLE_NAME, col, key, value, orderBy)
        } finally {
            close()
        }
    }

    fun queryLikeTestTableByKey(key: String, like: String, orderBy: String): Cursor {
        open()
        try {
            val col = arrayOf(TestTable.Column.KEY_ID, TestTable.Column.KEY_DATA)
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