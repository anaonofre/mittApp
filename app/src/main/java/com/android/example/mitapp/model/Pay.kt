package com.android.example.mitapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments_table")
data class Pay(
    @ColumnInfo("configured_card")
    val configuredCard: String,
    @ColumnInfo("recipient_card")
    val recipientCard: String,
    @ColumnInfo("recipient_name")
    val recipientName: String,
    @ColumnInfo("concept")
    val concept: String,
    @ColumnInfo("date_hour")
    var dateHour: Long = System.currentTimeMillis(),
    @ColumnInfo("location")
    val location: String = "MX"

    ) {
    @PrimaryKey(autoGenerate = true)
    var payId: Long = 0L




}





