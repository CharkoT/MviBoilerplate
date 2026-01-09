package com.charko.mviboilerplate.feature_x.datasource.local

import com.charko.mviboilerplate.feature_x.datasource.local.dao.AppDao
import com.charko.mviboilerplate.feature_x.domain.model.Counter
import com.charko.mviboilerplate.feature_x.repository.datasource.CounterDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterLocalDataSourceImpl @Inject constructor(
    private val appDao: AppDao
) : CounterDataSource {
    override suspend fun getCount(): Flow<Counter> {
        val entity = appDao.getCount()

        return flowOf(
            Counter(entity?.count?.toInt() ?: 0)
        )
    }

    override suspend fun increaseCounter() {
        appDao.insertAppEntity(AppEntity("0", (appDao.getCount()?.count ?: 0) + 1, System.currentTimeMillis()))
    }

    override suspend fun decreaseCounter() {
        appDao.insertAppEntity(AppEntity("0", (appDao.getCount()?.count ?: 0) - 1, System.currentTimeMillis()))
    }
}