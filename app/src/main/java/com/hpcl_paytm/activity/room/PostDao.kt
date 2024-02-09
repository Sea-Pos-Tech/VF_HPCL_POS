package com.hpcl_paytm.activity.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postList: List<Entity>)

    @Query("SELECT * FROM post ORDER BY id ASC")
    fun getAllPost(): Flow<List<Entity>>

}