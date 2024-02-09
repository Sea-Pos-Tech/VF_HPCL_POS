package com.hpcl_paytm.activity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
class Entity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}