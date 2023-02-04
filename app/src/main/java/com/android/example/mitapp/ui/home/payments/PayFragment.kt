package com.android.example.mitapp.ui.home.payments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.FragmentPayBinding
import com.android.example.mitapp.ui.home.HomeViewModel
import com.android.example.mitapp.model.Card
import com.android.example.mitapp.util.validCard
import com.google.android.material.snackbar.Snackbar




class PayFragment : Fragment() {

    private lateinit var binding: FragmentPayBinding

    private val homeViewModel by activityViewModels<HomeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false)


        val arrayCards= homeViewModel.cards.value?.toTypedArray()

        arrayCards?.let {
            binding.spinerCards.adapter= SpinnerAdapter(requireContext(),arrayCards)
        }





        binding.spinerCards.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCard = parent!!.getItemAtPosition(position) as Card

                homeViewModel.onSelectedCard(selectedCard)
            }
        }

        binding.btnPay.setOnClickListener {
            val validData=validDataPay()

            if (validData){
                createPay()
            }


        }

        homeViewModel.insertPay.observe(viewLifecycleOwner, Observer { insertCard->
            if (insertCard){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Pago realizado con éxito",
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()

                findNavController().navigate(R.id.movementsFragment)
                homeViewModel.onPayDone()
            }

        })

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun createPay(){
        val configuredCard=homeViewModel.cardSetUp.value?.number.toString()
        val holderCard = binding.txtInputRecipientCard.editText?.text.toString()
        val nameHolder = binding.txtInputRecipientName.editText?.text.toString()
        val concept = binding.txtInputConcept.editText?.text.toString()

        homeViewModel.addPay(configuredCard, holderCard, nameHolder, concept)
    }

    private fun validDataPay():Boolean{

        val validHolderCard=binding.txtRecipientCard.validCard()
        val validName=binding.txtRecipientName.text?.isEmpty()==false
        val validConcept=binding.txtConcept.text?.isEmpty()==false

        if (!validConcept) binding.txtInputConcept.setError("Concepto no válido")
        if (!validHolderCard) binding.txtInputRecipientCard.setError("Tarjeta no válida")
        if (!validName) binding.txtInputRecipientName.setError("Ingresa un nombre")

        return validConcept && validName && validHolderCard


    }




}