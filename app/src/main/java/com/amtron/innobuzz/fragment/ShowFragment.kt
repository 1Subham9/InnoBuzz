package com.amtron.innobuzz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amtron.innobuzz.R
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.FragmentSelectBinding
import com.amtron.innobuzz.databinding.FragmentShowBinding
import com.amtron.innobuzz.helper.Common.Companion.data
import com.amtron.innobuzz.helper.Common.Companion.position
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentShowBinding.inflate(inflater, container, false)


        return binding.root
    }




    override fun onResume() {


        if(position!=-1){
            binding.id.text = "Id: ${data[position].id}"
            binding.userId.text = "UserId: ${data[position!!].userId}"
            binding.title.text = "Title: ${data[position!!].title}"
            binding.body.text = "Body: ${data[position!!].body}"
        }

        super.onResume()
    }


}