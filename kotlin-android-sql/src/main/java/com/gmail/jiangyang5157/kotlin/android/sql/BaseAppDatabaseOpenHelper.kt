package com.gmail.jiangyang5157.kotlin.android.sql

/**
 * Created by Yang Jiang on June 26, 2017
 */
//abstract class BaseAppDatabaseOpenHelper protected constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
//
//    protected abstract val createTableSqlsOnCreate: Array<String>
//
//    protected abstract val dropTableNamesOnUpgrade: Array<String>
//
//    override fun onCreate(db: SQLiteDatabase) {
//        val sqls = createTableSqlsOnCreate
//        for (sql in sqls) {
//            db.execSQL(sql)
//            println(TAG + " execSQL: " + sql)
//        }
//    }
//
//    /**
//     * This will be called when you change version number.
//     */
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        println("$TAG Database going to upgrades from Version $oldVersion to Version $newVersion")
//        val names = dropTableNamesOnUpgrade
//        for (name in names) {
//            db.execSQL("drop table if exists " + name)
//            println(TAG + " Drop table: " + name)
//        }
//
//        onCreate(db)
//    }
//
//    companion object {
//        private val TAG = "[BAppDbOpenHelper]"
//    }
//}
