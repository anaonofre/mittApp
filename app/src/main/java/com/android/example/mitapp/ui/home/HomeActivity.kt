package com.android.example.mitapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.android.example.mitapp.MainActivity
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(){

    private lateinit var binding : ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(application)
    }

    private var navController: NavController? = null
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(binding.root)


        initializeNavController()

        onClickBottomMenuOption()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_close_sesion -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initializeNavController()
    {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment?.navController
    }

//navegar entre pantallas
    private fun onClickBottomMenuOption()= with(binding.bottomMenu){

        imvMovements.setOnClickListener{
            navController?.navigate(R.id.movementsFragment)
        }
        txtMovements.setOnClickListener{
            navController?.navigate(R.id.movementsFragment)
        }


        imvMyCards.setOnClickListener {
            navController?.navigate(R.id.myCardsFragment)
        }
        txtMyCards.setOnClickListener {
            navController?.navigate(R.id.myCardsFragment)
        }

        imvPay.setOnClickListener {
            navController?.navigate(R.id.payFragment)
        }
        txtPay.setOnClickListener {
            navController?.navigate(R.id.payFragment)
        }
    }






}