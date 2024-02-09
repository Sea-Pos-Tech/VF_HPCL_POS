package com.hpcl_paytm.activity.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

}