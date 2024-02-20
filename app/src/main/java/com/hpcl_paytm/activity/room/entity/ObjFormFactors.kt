package com.hpcl_paytm.activity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ObjFormFactors {
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0
    var FormFactorId: Int = 0
    var FormFactorName: String? = null
}
