package com.android.example.mitapp.ui.home.payments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.ItemPayBinding
import com.android.example.mitapp.databinding.ItemSpinnerBinding
import com.android.example.mitapp.model.Card


class SpinnerAdapter(private val context: Context, private val data: Array<Card>?) : BaseAdapter() {



    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data!!.size
    }

    override fun getItem(position: Int): Any {
        return data?.get(position) ?: Card("x", "12","1")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSpinnerBinding.inflate(layoutInflater, parent, false)


        val item = data?.get(position)
        if (item != null) {

            with(binding){
                txtNumCard.text= "Numero de Tarjeta: " + item.number
                txtTarjeatahabiente.text= "Nombre Tarjetahabiente: " + item.cardholderName
                txtExpirationDate.text="Fecha de expiracion: " +item.expirationDate
            }
        }

        return binding.root
    }
}
