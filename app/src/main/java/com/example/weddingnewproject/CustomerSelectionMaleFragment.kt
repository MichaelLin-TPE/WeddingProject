package com.example.weddingnewproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.weddingnewproject.customer_list.CustomerListActivity
import com.example.weddingnewproject.databinding.FragmentCustomerSelectionMaleBinding


class CustomerSelectionMaleFragment : Fragment() {
    private var _binding : FragmentCustomerSelectionMaleBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragmentActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentActivity = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_customer_selection_male,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            fragmentActivity.finish()
        }
        binding.lineage.setOnClickListener {
            val intent = Intent(fragmentActivity,CustomerListActivity::class.java)
            intent.putExtra("type",0) //0 男生的親友
            intent.putExtra("customerType","lineage")
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CustomerSelectionMaleFragment().apply {

            }
    }
}