package com.android.example.mitapp.ui.home.movements


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.mitapp.databinding.ItemPayBinding
import com.android.example.mitapp.model.Pay
import com.android.example.mitapp.util.convertLongToDateString


class PayAdapter : ListAdapter<Pay, PayAdapter.ViewHolder>(PayDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemPayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pay: Pay) {
            binding.txtConcepto.text= "Concepto: " + pay.concept
            binding.txtFecha.text = "Fecha y Hora: " + convertLongToDateString(pay.dateHour)
            binding.txtUbicacion.text = "Lugar: " + pay.location
            binding.txtNombreDestinatario.text = "Nombre Destinatario: " + pay.recipientName
            binding.txtTarjetaConfigurada.text = "Pago Realizado con Tarjeta:" + pay.configuredCard
            binding.txtTarjetaDestinatario.text = "Tarjeta del Destinario: " + pay.recipientCard


            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPayBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PayDiffCallback : DiffUtil.ItemCallback<Pay>() {

    override fun areItemsTheSame(oldItem: Pay, newItem:Pay): Boolean {
        return oldItem.payId == newItem.payId
    }


    override fun areContentsTheSame(oldItem: Pay, newItem: Pay): Boolean {
        return oldItem == newItem
    }


}

