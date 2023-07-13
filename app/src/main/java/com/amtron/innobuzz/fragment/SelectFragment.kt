package com.amtron.innobuzz.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amtron.innobuzz.adapter.PostAdapter
import com.amtron.innobuzz.api.PostService
import com.amtron.innobuzz.api.RetrofitHelper
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.FragmentSelectBinding
import com.amtron.innobuzz.helper.Common
import com.amtron.innobuzz.helper.Common.Companion.data
import com.amtron.innobuzz.helper.Common.Companion.position
import com.amtron.innobuzz.model.Post
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectFragment : Fragment(), PostAdapter.ItemClickInterface {

    private lateinit var binding: FragmentSelectBinding
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSelectBinding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = PostAdapter(this)
        binding.recycler.adapter = adapter


        val instance = RetrofitHelper.getInstance().create(PostService::class.java)
        val database = PostDatabase.getDataBase(requireContext().applicationContext).postDao()


        instance.getPost().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                if (response.isSuccessful) {

                    if (response.body() != null) {
                        data = Gson().fromJson(
                            response.body(),
                            object : TypeToken<List<Post>>() {}.type
                        )

                        Log.d(TAG, "Data $data")

                        GlobalScope.launch {
                            database.addPosts(data)
                            data = database.getPosts()

                        }

                        adapter.updateList(data)

                    }

                }

            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {

                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()

            }

        })


        return binding.root
    }

    override fun getPost(index: Int) {

        position = index
        Common.viewPage.currentItem = 1
    }


}