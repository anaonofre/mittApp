package com.android.example.mitapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table")
data class Card(
    @ColumnInfo(name="cardholder_name")
    val cardholderName: String,
    @ColumnInfo("number")
    val number: String,
    @ColumnInfo(name="expiration_date")
    val expirationDate: String,
        ){

    @PrimaryKey(autoGenerate = true)
    var cardId: Int = 0

}

