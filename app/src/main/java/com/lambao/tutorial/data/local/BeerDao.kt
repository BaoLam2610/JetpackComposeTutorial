package com.lambao.tutorial.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BeerDao {

    @Insert
    suspend fun upsertAll(beers : List<BeerEntity>)

    @Query("SELECT * FROM BeerEntity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM BeerEntity")
    suspend fun clearAll()
}