package com.hpcl_paytm.activity.room.dao

import androidx.room.*
import com.hpcl_paytm.activity.room.entity.Schema

@Dao
interface SchemaDao {

    @Insert
    fun insert(schema: Schema)

    @Update
    fun update(schema: Schema)

    @Delete
    fun delete(schema: Schema)

    @Query("delete from schema")
    fun deleteAllNotes()

    @Query("select * from schema")
    fun getAllFromSchema() : List<Schema>
}