package com.hpcl_paytm.activity.room.dao

import androidx.room.*
import com.hpcl_paytm.activity.room.entity.HpclBatchTransaction

@Dao
interface BatchTransactionDao {
    @Query("SELECT * FROM HpclBatchTransaction")
    fun getAll(): List<HpclBatchTransaction?>?

    @Query("SELECT * FROM hpclbatchtransaction WHERE transactionDate = :datetime")
    fun getTodayTransaction(datetime: String): List<HpclBatchTransaction>

    @Insert
    fun insertAll(users: HpclBatchTransaction)

    @Query("DELETE FROM HpclBatchTransaction")
    fun deleteAll()


    @Query("SELECT * FROM hpclbatchtransaction WHERE TransactionType = :transType AND batch_Id = :batchNum")
    fun getActiveTransactionsLastBatch(transType: String, batchNum: Int?) : List<HpclBatchTransaction?>?


    @Query("SELECT * FROM hpclbatchtransaction WHERE product = :product AND batch_Id = :batchNum")
    fun getProductFromBatch(product: String?, batchNum: Int?) : List<HpclBatchTransaction?>?

    @Query("SELECT * FROM HpclBatchTransaction ORDER BY transaction_Date DESC LIMIT 1")
    fun lastBatchTransaction(): List<HpclBatchTransaction?>?

    @Query("SELECT * FROM HpclBatchTransaction WHERE batch_Id = :batchNum")
    fun lastBatchAllTransaction(batchNum: Int?): List<HpclBatchTransaction?>?

    @Query("SELECT * FROM HpclBatchTransaction WHERE OperatorId = :operatorId AND batch_Id =:batchNum")
    fun getAlltransactionFromCurrentOperator(operatorId: String?, batchNum: Int?): List<HpclBatchTransaction?>?

    @Query("SELECT * FROM hpclbatchtransaction WHERE TransactionType = :transType AND batch_Id = :batchNum AND OperatorId = :operatorId")
    fun getActiveTransactionsFromCurrentOperator(transType: String, batchNum: Int?,operatorId: String?) : List<HpclBatchTransaction?>?

    @Query("SELECT * FROM hpclbatchtransaction WHERE product = :product AND batch_Id = :batchNum AND OperatorId = :operatorId")
    fun getProducFromCurrentOperatortLastBatch(product: String?, batchNum: Int?,operatorId: String?) : List<HpclBatchTransaction?>?

}