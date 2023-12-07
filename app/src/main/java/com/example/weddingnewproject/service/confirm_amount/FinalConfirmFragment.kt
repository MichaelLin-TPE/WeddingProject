package com.example.weddingnewproject.service.confirm_amount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.service.waiting_page.WaitingFragment


class FinalConfirmFragment : Fragment() {

    private lateinit var viewModel: FinalConfirmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FinalConfirmViewModel::class.java]
        val type = arguments?.getInt("type",0)
        viewModel.onViewCreated(type)
        handleLiveData(type)
    }

    private fun handleLiveData(type: Int?) {


        viewModel.backToWaitingPageLiveData.observe(viewLifecycleOwner){
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentManager?.let {
                for (fragment in it.fragments){
                    fragmentTransaction?.remove(fragment)
                }
                val waitingFragment = WaitingFragment.newInstance(type!!)
                fragmentTransaction?.replace(R.id.container, waitingFragment)
                fragmentTransaction?.commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
            FinalConfirmFragment().apply {
                arguments = Bundle().apply {
                    putInt("type",type)
                }
            }
    }
}