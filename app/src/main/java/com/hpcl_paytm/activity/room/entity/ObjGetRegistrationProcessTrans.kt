package com.hpcl_paytm.activity.room.entity

import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.Serializable

@Entity
class ObjGetRegistrationProcessTrans:Serializable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
    var TransType: Int = 0
    var SerialNo: Int = 0
    var TransName: String? = null
    var Type: String? = null
    var ServiceStatus: Int = 0
    var Carded: Int = 0
    var Cardless: Int = 0
    var NonCarded: Int = 0
    var MinVal: Long = 0
    var MaxVal: Int = 0
    var ParentID:Int = 0
    @TypeConverters(TypeConverter::class) // add here
    var ObjGetParentTransTypeDetail= listOf<ObjGetParentTransTypeDetail>()

}