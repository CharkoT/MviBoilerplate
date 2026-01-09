package com.charko.mviboilerplate.feature_x.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.charko.mviboilerplate.feature_x.datasource.local.AppEntity
import com.charko.mviboilerplate.feature_x.datasource.local.dao.AppDao

@Database(entities = [AppEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}