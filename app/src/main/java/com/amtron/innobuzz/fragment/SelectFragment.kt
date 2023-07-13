package com.amtron.innobuzz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amtron.innobuzz.R
import com.amtron.innobuzz.databinding.FragmentSelectBinding


class SelectFragment : Fragment() {

    private lateinit var binding: FragmentSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectBinding.inflate(inflater, container, false)



        return binding.root
    }


}