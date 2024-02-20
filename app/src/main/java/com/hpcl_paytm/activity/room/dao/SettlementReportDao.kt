package com.hpcl_paytm.activity.room.dao

import androidx.room.*
import com.hpcl_paytm.activity.room.entity.SettlementReportInfo

@Dao
interface SettlementReportDao {

    @Insert
    fun insertSettlementReportInfo(users: SettlementReportInfo)
}