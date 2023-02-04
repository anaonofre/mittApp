package com.android.example.mitapp.ui.home.myCards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.FragmentMyCardsBinding
import com.android.example.mitapp.ui.home.HomeViewModel


class MyCardsFragment : Fragment() {


    private val homeViewModel by activityViewModels<HomeViewModel>()

    lateinit var binding: FragmentMyCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentMyCardsBinding>(inflater, R.layout.fragment_my_cards, container, false)


        binding.homeViewModel=homeViewModel

        val adapter = CardsAdapter()
        binding.recyclerUserCards.adapter = adapter

        homeViewModel.cards.observe(viewLifecycleOwner, Observer { cardsList ->
            if (cardsList.isEmpty()){
                binding.txtWithoutCards.visibility=View.VISIBLE
                binding.recyclerUserCards.visibility=View.GONE
            }else{
                binding.txtWithoutCards.visibility=View.GONE
                binding.recyclerUserCards.visibility=View.VISIBLE
                cardsList?.let {
                    adapter.submitList(cardsList)
                }
            }
        })

        binding.btnNewCard.setOnClickListener {

            findNavController().navigate(R.id.action_myCardsFragment_to_addCardFragment)
        }



        return binding.root
    }



}