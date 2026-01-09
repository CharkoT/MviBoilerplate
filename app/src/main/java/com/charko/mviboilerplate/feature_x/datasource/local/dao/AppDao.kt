package com.charko.mviboilerplate.feature_x.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charko.mviboilerplate.feature_x.datasource.local.AppEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM app_table WHERE id = :id")
    fun getCount(id: String = "0"): AppEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppEntity(entity: AppEntity)
}
