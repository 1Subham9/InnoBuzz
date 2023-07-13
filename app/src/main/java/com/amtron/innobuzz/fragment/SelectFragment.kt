package com.amtron.innobuzz.fragment

import android.os.Bundle
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

        position = -1

        val instance = RetrofitHelper.getInstance().create(PostService::class.java)
        val database = PostDatabase.getDataBase(requireContext().applicationContext).postDao()


        instance.getPost().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                if (response.isSuccessful) {

                    if (response.body() != null) {
                        val result : List<Post> = Gson().fromJson(
                            response.body(),
                            object : TypeToken<List<Post>>() {}.type
                        )


                        GlobalScope.launch {
                            database.addPosts(result)
                            data = database.getPosts()

                        }

                        adapter.updateList(result)

                    }

                }

            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {

                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()

            }

        })


        return binding.root
    }

    override fun getPost(position: Int) {

        Common.position = position
        Common.viewPage.currentItem = 1
    }


}