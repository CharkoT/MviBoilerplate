package com.charko.mviboilerplate.feature_x.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_table")
data class AppEntity(
    @PrimaryKey val id: String,
    val count: Long,
    val updateDate: Long,
)
