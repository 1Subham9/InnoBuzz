package com.amtron.innobuzz.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amtron.innobuzz.R
import com.amtron.innobuzz.adapter.PostAdapter
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.FragmentSelectBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SelectFragment : Fragment(), PostAdapter.ItemClickInterface {

    private lateinit var binding: FragmentSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectBinding.inflate(inflater, container, false)

        val database = PostDatabase.getDataBase(requireContext().applicationContext).postDao()

        GlobalScope.launch {
            val data = database.getPosts()

            Log.d(TAG, "DataBase $data")
        }

        return binding.root
    }

    override fun getPost(position: Int) {

    }


}