package com.example.weddingnewproject.customer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.weddingnewproject.R
import com.example.weddingnewproject.customer_list.CustomerListActivity
import com.example.weddingnewproject.databinding.FragmentCustomerSelectionFemaleBinding


class CustomerSelectionFemaleFragment : Fragment() {

    private var _binding: FragmentCustomerSelectionFemaleBinding? = null
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
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_customer_selection_female,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            fragmentActivity.finish()
        }
        binding.family.setOnClickListener {
            val intent = Intent(fragmentActivity, CustomerListActivity::class.java)
            intent.putExtra("type",1) //1 女生的親友
            intent.putExtra("customerType","family")
            startActivity(intent)
            Log.i("Michael","Click brotherSister")
        }
        binding.friend.setOnClickListener {
            val intent = Intent(fragmentActivity, CustomerListActivity::class.java)
            intent.putExtra("type",1) //1 女生的親友
            intent.putExtra("customerType","friend")
            startActivity(intent)
            Log.i("Michael","Click friend")
        }
        binding.brotherSister.setOnClickListener {
            val intent = Intent(fragmentActivity, CustomerListActivity::class.java)
            intent.putExtra("type",1) //1 女生的親友
            intent.putExtra("customerType","brother_sister")
            startActivity(intent)
            Log.i("Michael","Click brotherSister")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CustomerSelectionFemaleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}