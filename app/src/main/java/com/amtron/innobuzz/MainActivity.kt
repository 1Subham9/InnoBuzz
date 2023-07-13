package com.amtron.innobuzz

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.amtron.innobuzz.adapter.ViewPagerAdapter
import com.amtron.innobuzz.api.PostService
import com.amtron.innobuzz.api.RetrofitHelper
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.ActivityMainBinding
import com.amtron.innobuzz.fragment.SelectFragment
import com.amtron.innobuzz.fragment.ShowFragment
import com.amtron.innobuzz.helper.Common
import com.amtron.innobuzz.model.Post
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var messageDialog : AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)







        val fragmentList = arrayListOf<Fragment>(
           SelectFragment(),ShowFragment()
        )


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)


        val adapter = ViewPagerAdapter(
            fragmentList, this.supportFragmentManager, lifecycle
        )

        val viewPage = findViewById<ViewPager2>(R.id.viewPager)

        viewPage.adapter = adapter

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.posts -> viewPage.currentItem = 0

                R.id.info -> viewPage.currentItem = 1

            }

            return@setOnItemSelectedListener true
        }



        viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                when (position) {

                    0 -> bottomNav.selectedItemId = R.id.posts
                    1 -> bottomNav.selectedItemId = R.id.info

                }

                super.onPageSelected(position)
            }


        })


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                when (viewPage.currentItem) {


                    1 -> {
                        viewPage.currentItem = 0
                    }
                    else -> {

                        val builder = AlertDialog.Builder(this@MainActivity)

                        builder.setTitle("EXIT")
                        //set message for alert dialog
                        builder.setMessage("Are you sure you want to exit the app?")
                        builder.setIcon(R.drawable.baseline_exit_to_app_24)

                        //performing positive action
                        builder.setPositiveButton("Confirm"){dialogInterface, which ->
                            finish()
                        }
                        builder.setNegativeButton("Cancel"){dialogInterface, which ->
                            messageDialog.dismiss()
                        }
                        // Create the AlertDialog
                        messageDialog = builder.create()
                        // Set other dialog properties
                        messageDialog.setCancelable(false)
                        messageDialog.show()

                    }
                }

            }
        }

        onBackPressedDispatcher.addCallback(callback)





    }
}