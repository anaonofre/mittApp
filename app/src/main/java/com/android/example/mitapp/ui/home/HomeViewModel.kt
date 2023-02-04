package com.android.example.mitapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.example.mitapp.dataBase.MitDataBase
import com.android.example.mitapp.model.Card
import com.android.example.mitapp.model.Pay
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database by lazy {
        MitDataBase.getInstance(application)
    }


    val cards = database.mitDao.getCards()

    val payments = database.mitDao.getPayments()

    private var _insertCard = MutableLiveData<Boolean>()
    val insertCard: LiveData<Boolean>
        get() = _insertCard

    private var _insertPay = MutableLiveData<Boolean>()
    val insertPay: LiveData<Boolean>
        get() = _insertPay


    private var _cardSetUp = MutableLiveData<Card>()
    val cardSetUp: LiveData<Card>
        get() = _cardSetUp


    //------------ C A R D S -------------------------------------
    fun addCard(name: String, number: String, date: String) {
        val c = Card(name, number, date)
        viewModelScope.launch {
            insertCardDatabase(c)
            _insertCard.value = true
        }
    }

    private suspend fun insertCardDatabase(c: Card) {
        database.mitDao.insertCard(c)


    }

    fun onDoneNavigationAddCardToMyCards() {
        _insertCard.value = false
    }

    //----------- P a y m e n t s --------------------------

    fun addPay(
        cofiguredCard: String,
        recipientCard: String,
        recipientName: String,
        concept: String
    ) {
        val p = Pay(cofiguredCard, recipientCard, recipientName, concept)
        viewModelScope.launch {
            insertPayDatabase(p)
            _insertPay.value = true
        }
    }

    private suspend fun insertPayDatabase(pay: Pay) {
        database.mitDao.insertPay(pay)

    }

    fun onPayDone() {
        _insertPay.value = false
    }

    fun onSelectedCard(c: Card){
        _cardSetUp.value=c

    }


}