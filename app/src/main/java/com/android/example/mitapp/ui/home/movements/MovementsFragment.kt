package com.android.example.mitapp.ui.home.movements

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.FragmentMovementsBinding
import com.android.example.mitapp.ui.home.HomeViewModel


class MovementsFragment : Fragment() {


    private lateinit var binding: FragmentMovementsBinding

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movements, container, false)


        val adapter = PayAdapter()
        binding.recyclerUserMovements.adapter= adapter


        homeViewModel.payments.observe(viewLifecycleOwner, Observer { listPayments->

            if (listPayments.isEmpty()){
                Log.i("n", "sin datos")
                binding.recyclerUserMovements.visibility= View.GONE
                binding.labelNoPayments.visibility = View.VISIBLE

            }else
            {
                Log.i("n", "**********datos pay****** $listPayments")
                binding.recyclerUserMovements.visibility=View.VISIBLE
                binding.labelNoPayments.visibility = View.GONE
                listPayments?.let {
                    adapter.submitList(it)
                }

            }

        })

        // Inflate the layout for this fragment
        return binding.root
    }

}