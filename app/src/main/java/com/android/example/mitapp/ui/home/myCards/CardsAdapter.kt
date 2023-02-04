package com.android.example.mitapp.ui.home.myCards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.mitapp.databinding.ItemCardBinding
import com.android.example.mitapp.model.Card


class CardsAdapter : ListAdapter<Card, CardsAdapter.ViewHolder>(CardsDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.txtTarjeatahabiente.text="Tarjeta Habiente: "+card.cardholderName
            binding.txtNumCard.text="Numero de Tarjeta: "+card.number
            binding.txtExpirationDate.text="Fecha de Expiración: "+card.expirationDate
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CardsDiffCallback : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardId == newItem.cardId
    }


    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }


}