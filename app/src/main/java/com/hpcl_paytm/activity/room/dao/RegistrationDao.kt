package com.hpcl_paytm.activity.room.dao

import androidx.room.*
import com.hpcl_paytm.activity.room.entity.*

@Dao
interface RegistrationDao {

    @Insert
    fun insertObjGetRegistrationProcessMerchant(objGetRegistrationProcessMerchant: ObjGetRegistrationProcessMerchant)

    @Insert
    fun insertObjGetRegistrationProcessTrans(objGetRegistrationProcessTrans: ObjGetRegistrationProcessTrans)

    @Update
    fun update(objGetRegistrationProcessTrans: ObjGetRegistrationProcessTrans)

    @Insert
    fun insertObjProducts(objProducts: ObjProducts)

    @Insert
    fun insertObjBanks(objBanks: ObjBanks)

    @Insert
    fun insertFormFactors(objFormFactors: ObjFormFactors)


    /* @Insert
     fun insertTransCCMSRecharges(objCCMSRecharge: ObjCCMSRecharge)

     @Insert
     fun insertTransReload(objReload: ObjReload)

     @Insert
     fun insertTransDriverLoyalty(objDriverLoyalty: ObjDriverLoyalty)

     @Insert
     fun insertTransPayback(objPayback: ObjPayback)*/

    @Query("SELECT * FROM ObjGetRegistrationProcessMerchant")
    fun fetchMerchantDetails(): ObjGetRegistrationProcessMerchant

    @Query("SELECT * FROM objgetregistrationprocesstrans")
    fun fetchTransList(): List<ObjGetRegistrationProcessTrans>

    @Query("SELECT * FROM objproducts")
    fun fetchProductList(): List<ObjProducts>

    @Query("SELECT * FROM objbanks")
    fun fetchBankList(): List<ObjBanks>

    @Query("SELECT * FROM ObjFormFactors")
    fun fetchFormFactorList(): List<ObjFormFactors>

    /*    @Query("SELECT * FROM objccmsrecharge")
        fun fetchCCMSRechargeList(): List<ObjCCMSRecharge>

        @Query("SELECT * FROM ObjReload")
        fun fetchReloadList(): List<ObjReload>

        @Query("SELECT * FROM objdriverloyalty")
        fun fetchDriverLoyaltyList(): List<ObjDriverLoyalty>

        @Query("SELECT * FROM ObjPayback")
        fun fetchPaybackList(): List<ObjPayback>*/

    @Delete
    fun deleteObjGetRegistrationProcessMerchant(objGetRegistrationProcessMerchant: ObjGetRegistrationProcessMerchant)

    @Delete
    fun deleteObjGetRegistrationProcessTrans(objGetRegistrationProcessTrans: List<ObjGetRegistrationProcessTrans>)

    @Delete
    fun deleteFormFactor(objFormFactors: List<ObjFormFactors>)

    @Delete
    fun deleteBankList(objBanks: List<ObjBanks>)

    @Delete
    fun deleteProductList(objProducts: List<ObjProducts>)

}