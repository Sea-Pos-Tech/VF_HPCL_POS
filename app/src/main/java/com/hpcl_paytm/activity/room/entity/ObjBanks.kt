package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ObjBanks {
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0
    var FastagId: Int = 0
    var FastagName: String? = null
}
