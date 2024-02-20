package com.hpcl_paytm.activity.room.dao

import androidx.room.*
import com.hpcl_paytm.activity.room.entity.Operators

@Dao
interface OperatorsDao {

    @Insert
    fun insertOperatorInfo(operators: Operators)

    @Query("SELECT * FROM Operators")
    fun getAllOperators(): List<Operators?>?

    @Query("SELECT * FROM Operators WHERE operatorId = :operatorId AND password =:password")
    fun getOperator(operatorId: String,password: String): Operators

    @Query("DELETE FROM Operators WHERE operatorId = :operatorId")
    fun deleteOperator(operatorId: String)

    @Query("UPDATE Operators SET isLogon =:isLogin WHERE operatorId =:operatorId")
    fun updateOperator(isLogin: Boolean, operatorId: String)

    @Query("UPDATE Operators SET isLogon=:isLogin,lastLogonDate= :loginDate WHERE operatorId =:operatorId")
    fun updateOperatorLoginDateTime(
        isLogin: Boolean,
        operatorId: String,
        loginDate: String
    )

    @Query("SELECT * FROM Operators WHERE isLogon =:isLogin")
    fun getLgoinOperator(isLogin: Boolean): Operators
}
