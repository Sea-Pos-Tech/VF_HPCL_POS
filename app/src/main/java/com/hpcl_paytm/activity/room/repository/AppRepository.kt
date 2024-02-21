package com.hpcl_paytm.activity.room.repository

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.hpcl_paytm.activity.room.AppRoomDatabase
import com.hpcl_paytm.activity.room.entity.HpclBatchTransaction
import com.hpcl_paytm.activity.room.entity.HpclTransaction
import com.hpcl_paytm.activity.room.entity.ObjBanks
import com.hpcl_paytm.activity.room.entity.ObjFormFactors
import com.hpcl_paytm.activity.room.entity.ObjGetRegistrationProcessMerchant
import com.hpcl_paytm.activity.room.entity.ObjGetRegistrationProcessTrans
import com.hpcl_paytm.activity.room.entity.ObjProducts
import com.hpcl_paytm.activity.room.entity.Operators
import com.hpcl_paytm.activity.room.entity.Schema
import com.hpcl_paytm.activity.room.entity.SettlementReportInfo
import com.hpcl_paytm.activity.room.entity.SumAveragePojo
import javax.inject.Inject


class AppRepository() {

    @Inject
    lateinit var hpclDatabase: AppRoomDatabase
   // private var transactionDataBase: TransactionDataBase
   // private var registrationDatabase: RegistartionDataBase
   // private var settlementReportDataBase: SettlementReportDataBase
    // private var operatorsDatabase: OperatorsDatabase
  //  private var transactionBatchDataBase: TransactionBatchDataBase

    //private val operators = "operators"
      // Insert new user
    fun insertUser(schema: Schema) {
        hpclDatabase.schemaDao().insert(schema)
    }
    // update user
    fun updateUser(schema: Schema) {
        hpclDatabase.schemaDao().update(schema)
    }
    // Delete user
    fun deleteUser(schema: Schema) {
        hpclDatabase.schemaDao().delete(schema)
    }

    fun getAllRecordsFromSchema(): List<Schema>? {
        return hpclDatabase?.schemaDao()?.getAllFromSchema()
    }

    fun getAllTransaction(): List<HpclTransaction?>? {
        return hpclDatabase?.transactionDao()?.getAll()
    }

    fun getAllTransactionById(userIds: IntArray?): List<HpclTransaction?>? {
        return hpclDatabase?.transactionDao()?.loadAllByIds(userIds)
    }

    fun getLastTransaction(): HpclTransaction? {
       // Log.e("app repo db"," "+ Gson().toJson(transactionDataBase))
        Log.e("lastTransaction in apprepo"," "+ Gson().toJson( hpclDatabase?.transactionDao()?.lastTransaction()))
        return hpclDatabase?.transactionDao()?.lastTransaction()
    }

    fun insertTransaction(users: HpclTransaction?) {
        hpclDatabase?.transactionDao()?.insertAll(users!!)
    }

    //Action for Batch Table
    fun insertBatchTransaction(users: HpclBatchTransaction?) {
        hpclDatabase?.batchTransactionDao()?.insertAll(users!!)
    }

    fun deleteBatchTransaction() {
        hpclDatabase?.transactionDao()?.deleteAll()
    }

    fun getAllBatchTransaction(): List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getAll()
    }

    fun getAllTodayBatchTransaction(datetime: String):List<HpclBatchTransaction>?{
        return hpclDatabase?.batchTransactionDao()?.getTodayTransaction(datetime)
    }

    fun getLastBatchTransaction(): List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.lastBatchTransaction()
    }

    fun getAllLastBatchTransaction(batchNum: Int?): List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.lastBatchAllTransaction(batchNum)
    }

    fun deleteAllTransaction() {
        hpclDatabase?.transactionDao()?.deleteAll()
    }

    fun getActiveTransactions(transType: Int, batchNum: Int) : List<HpclTransaction?>? {
        return hpclDatabase?.transactionDao()?.getActiveTransactions(transType.toString(), batchNum)
    }

    fun getTransactionsForBatchNUmber(batchNum: Int) : List<HpclTransaction?>? {
        return hpclDatabase?.transactionDao()?.getTransactionsForBatchNUmber(batchNum)
    }

    fun insertMerchantDetails(objGetRegistrationProcessMerchant : ObjGetRegistrationProcessMerchant) {
        hpclDatabase?.registrationDao()?.insertObjGetRegistrationProcessMerchant(objGetRegistrationProcessMerchant)
    }


    fun insertTransDetails(objGetRegistrationProcessTrans: ObjGetRegistrationProcessTrans) {
        hpclDatabase?.registrationDao()?.insertObjGetRegistrationProcessTrans(objGetRegistrationProcessTrans)
    }

    fun deleteMerchant(objGetRegistrationProcessMerchant: ObjGetRegistrationProcessMerchant) {
        hpclDatabase.registrationDao()?.deleteObjGetRegistrationProcessMerchant(objGetRegistrationProcessMerchant)
    }

    fun deleteTransDetails(objGetRegistrationProcessTrans: List<ObjGetRegistrationProcessTrans>) {
        hpclDatabase.registrationDao().deleteObjGetRegistrationProcessTrans(objGetRegistrationProcessTrans)
    }
    fun deleteProduct(objProducts: List<ObjProducts>) {
       hpclDatabase.registrationDao()?.deleteProductList(objProducts)
    }

    fun deleteFormFactor(objFormFactors: List<ObjFormFactors>) {
       hpclDatabase.registrationDao()?.deleteFormFactor(objFormFactors)
    }

    fun deleteBankList(objBanks: List<ObjBanks>) {
        hpclDatabase.registrationDao().deleteBankList(objBanks)
    }

    fun insertObjBanks(objBanks: ObjBanks) {
        hpclDatabase.registrationDao().insertObjBanks(objBanks)
    }
    fun insertProducts(objProducts: ObjProducts) {
        hpclDatabase.registrationDao().insertObjProducts(objProducts)
    }


    fun insertFormFactors(objFormFactors: ObjFormFactors) {
        hpclDatabase.registrationDao().insertFormFactors(objFormFactors)
    }

    fun getMerchantDetails() : ObjGetRegistrationProcessMerchant? {
        return hpclDatabase.registrationDao()?.fetchMerchantDetails()
    }

    fun getBankList(): List<ObjBanks>?{
        return hpclDatabase.registrationDao().fetchBankList()
    }

    fun getProductList(): List<ObjProducts>?{
        return hpclDatabase.registrationDao().fetchProductList()
    }

    fun getFormfactorList(): List<ObjFormFactors>?{
        return hpclDatabase.registrationDao().fetchFormFactorList()
    }

    fun getTransList(): List<ObjGetRegistrationProcessTrans>?{
        return hpclDatabase.registrationDao().fetchTransList()
    }
    /*
        fun getTransCCMSRechargeList(): List<ObjCCMSRecharge>{
            return registrationDatabase.registartionDao().fetchCCMSRechargeList()
        }

        fun getTransDriverLoyaltyList(): List<ObjDriverLoyalty>{
            return registrationDatabase.registartionDao().fetchDriverLoyaltyList()
        }
        fun getTransReloadList(): List<ObjReload>{
            return registrationDatabase.registartionDao().fetchReloadList()
        }

        fun getTransPaybackList(): List<ObjPayback>{
            return registrationDatabase.registartionDao().fetchPaybackList()
        }*/
    fun getTransactionBasedOnId(invoiceId: Int) : HpclTransaction? {
        return hpclDatabase.transactionDao().getTransactionBasedOnId(invoiceId)
    }

    fun getTransactionsFromProductId(product: String, batchNum: Int) : List<HpclTransaction?>? {
        return hpclDatabase?.transactionDao()?.getTransactionsFromProductId(product, batchNum)
    }

    fun insertSettlementReportData(settlementReportInfo: SettlementReportInfo) {
        hpclDatabase?.settlementReportDao()?.insertSettlementReportInfo(settlementReportInfo)
    }

    fun getSumOfTrans(category: String, batchNum: Int) : SumAveragePojo? {
        return hpclDatabase.transactionDao()?.getCountAndSumForTransactions(category, batchNum)
    }

    fun getAllOperators() : List<Operators?>? {
        return hpclDatabase.operatorsDao()?.getAllOperators()
    }

    fun insertOperator(operators: Operators) {
        hpclDatabase.operatorsDao()?.insertOperatorInfo(operators)
    }

    fun getOperator(operatorId: String, password: String) : Operators? {
        return hpclDatabase.operatorsDao()?.getOperator(operatorId, password)
    }

    fun deleteOperator(operatorId: String) {
        hpclDatabase?.operatorsDao()?.deleteOperator(operatorId)
    }

    fun updateOperator(isLogin: Boolean, operatorId: String) {
        hpclDatabase?.operatorsDao()?.updateOperator(isLogin, operatorId)
    }

    fun updateOperatorLoginDateTime(isLogin: Boolean, operatorId: String,loginDate: String) {
        hpclDatabase?.operatorsDao()?.updateOperatorLoginDateTime(isLogin, operatorId,loginDate)
    }

    fun getLgoinOperator(isLogin: Boolean) : Operators? {
        return hpclDatabase?.operatorsDao()?.getLgoinOperator(isLogin)
    }

    fun getTotalAmount(batchNum: Int): Float?{
        return hpclDatabase?.transactionDao()?.getTotalAmountofCurrentBatch(batchNum)
    }

    fun getTotalofoneTransaction(batchNum: Int, transactionType:String): Float?{
        return hpclDatabase?.transactionDao()?.getTotalAmountofCurrentTransaction(batchNum,transactionType)
    }

    fun getCountTrans(category: String, batchNum: Int, transactionType: String): Long?{
        return hpclDatabase?.transactionDao()?.getCountTransaction(category, batchNum,transactionType)
    }

    fun getSumofSale(category: String, batchNum: Int, transactionType: String): Float?{
        return hpclDatabase?.transactionDao()?.getCountAndSumOfSale(category, batchNum,transactionType)
    }

    fun getActiveTransactionsLastBatch(transType: Int, batchNum: Int?) : List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getActiveTransactionsLastBatch(transType.toString(), batchNum)
    }

    fun getProductLastBatch(product: String?, batchNum: Int?) : List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getProductFromBatch(product, batchNum)
    }

    fun getAllTxnFromCurrentOperator(operatorId: String, batchNum: Int?): List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getAlltransactionFromCurrentOperator(operatorId,batchNum)
    }

    fun getActiveTransactionsFromCurrentOperator(transType: Int, batchNum: Int?,operatorId: String) : List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getActiveTransactionsFromCurrentOperator(transType.toString(), batchNum,operatorId)
    }
    fun getProducFromCurrentOperatortLastBatch(product: String?, batchNum: Int?,operatorId: String) : List<HpclBatchTransaction?>? {
        return hpclDatabase?.batchTransactionDao()?.getProducFromCurrentOperatortLastBatch(product, batchNum,operatorId)
    }

}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE Transaction_backup (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, firstName TEXT NOT NULL, age INTEGER NOT NULL)")
        }
    }
}

val MIGRATION_6_7: Migration = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("ALTER TABLE ObjGetRegistrationProcessMerchant ADD COLUMN Name TEXT")
            // execSQL("CREATE TABLE Transaction_backup (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, firstName TEXT NOT NULL, age INTEGER NOT NULL)")
            // execSQL("ALTER TABLE foo ADD COLUMN new_column INTEGER DEFAULT 0");
        }
    }
}
val MIGRATION_5_6: Migration = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE Transaction_backup (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, firstName TEXT NOT NULL, age INTEGER NOT NULL)")
        }
    }
}




