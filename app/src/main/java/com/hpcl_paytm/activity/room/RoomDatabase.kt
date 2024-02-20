package com.hpcl_paytm.activity.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hpcl_paytm.activity.room.dao.BatchTransactionDao
import com.hpcl_paytm.activity.room.dao.OperatorsDao
import com.hpcl_paytm.activity.room.dao.RegistrationDao
import com.hpcl_paytm.activity.room.dao.SchemaDao
import com.hpcl_paytm.activity.room.dao.SettlementReportDao
import com.hpcl_paytm.activity.room.dao.TransactionDao
import com.hpcl_paytm.activity.room.entity.HpclBatchTransaction
import com.hpcl_paytm.activity.room.entity.HpclTransaction
import com.hpcl_paytm.activity.room.entity.ObjBanks
import com.hpcl_paytm.activity.room.entity.ObjFormFactors
import com.hpcl_paytm.activity.room.entity.ObjGetParentTransTypeDetail
import com.hpcl_paytm.activity.room.entity.ObjGetRegistrationProcessMerchant
import com.hpcl_paytm.activity.room.entity.ObjGetRegistrationProcessTrans
import com.hpcl_paytm.activity.room.entity.ObjProducts
import com.hpcl_paytm.activity.room.entity.Operators
import com.hpcl_paytm.activity.room.entity.Schema
import com.hpcl_paytm.activity.room.entity.SettlementReportInfo
import com.hpcl_paytm.activity.room.entity.SumAveragePojo


@Database(
    entities = [HpclBatchTransaction::class, HpclTransaction::class,
        ObjBanks::class, ObjFormFactors::class, ObjGetParentTransTypeDetail::class,
        ObjGetRegistrationProcessMerchant::class, ObjGetRegistrationProcessTrans::class,
        ObjProducts::class, Operators::class, Schema::class, SettlementReportInfo::class],
    version = 4,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun operatorsDao(): OperatorsDao
    abstract fun registrationDao(): RegistrationDao
    abstract fun settlementReportDao(): SettlementReportDao
    abstract fun schemaDao(): SchemaDao
    abstract fun batchTransactionDao(): BatchTransactionDao
    abstract fun transactionDao(): TransactionDao


}