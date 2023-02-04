package com.android.example.mitapp.ui.home.myCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.FragmentAddCardBinding
import com.android.example.mitapp.ui.home.HomeViewModel
import com.android.example.mitapp.util.validCard
import com.google.android.material.snackbar.Snackbar


class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddCardBinding>(inflater, R.layout.fragment_add_card, container, false)

        binding.btnAddCard.setOnClickListener {

            with(binding){

                val validaCard=editCardNumber.validCard()
                val validCardHolder = editCardholderName.text?.isEmpty()==false
                val validDate = editExpirationDate.text?.isEmpty()==false

                if (!validaCard) txtInputCardNumber.setError("Numero de tarjeta no válido")
                if (!validCardHolder) txtInputCardholderName.setError("Ingresa un nombre válido")
                if(!validDate) txtInputExpirationDate.setError("Fecha no válida")

                if (validaCard && validCardHolder && validDate){
              
                    btnAddCard.visibility=View.GONE
                    progressCircular.visibility=View.VISIBLE
                    homeViewModel.addCard(editCardholderName.text.toString(), editCardNumber.text.toString(), editExpirationDate.text.toString())
                }

            }

        }

        homeViewModel.insertCard.observe(viewLifecycleOwner, Observer { insertCard->
           if (insertCard){
               binding.progressCircular.visibility=View.GONE



               Snackbar.make(
                   requireActivity().findViewById(android.R.id.content),
                   getString(R.string.card_added_message),
                   Snackbar.LENGTH_SHORT // How long to display the message.
               ).show()

               findNavController().navigate(R.id.action_addCardFragment_to_myCardsFragment)

               homeViewModel.onDoneNavigationAddCardToMyCards()

           }

        })


        // Inflate the layout for this fragment
        return binding.root
    }


}