package com.android.example.mitapp.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.example.mitapp.model.Pay
import com.android.example.mitapp.model.Card
import com.android.example.mitapp.model.User
@Dao
interface MitDatabaseDao {

    @Insert
    suspend fun insertCard(card: Card)

    @Insert
    suspend fun insertPay(pay: Pay)

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM payments_table ORDER BY date_hour DESC")
    fun getPayments(): LiveData<List<Pay>>

    @Query("SELECT * FROM cards_table")
    fun getCards() : LiveData<List<Card>>


}