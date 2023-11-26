package com.example.weddingnewproject.customer_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData

class CustomerListAdapter(private var dataList:ArrayList<CustomerListData>,private val customerCallBack: customerCallBack) : RecyclerView.Adapter<CustomerListAdapter.ViewModel>() {

    private lateinit var context : Context

    fun update(dataList : ArrayList<CustomerListData>){
        this.dataList = dataList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer_list_layout,parent,false)
        context = parent.context
        return ViewModel(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        val data = dataList[position]
        holder.tvName.text = data.name
        holder.tvName.setOnClickListener {
            customerCallBack(data)
        }
        if (data.isSelected){
            holder.tvName.setBackgroundColor(ContextCompat.getColor(context,R.color.customer_active))
        } else{
            holder.tvName.setBackgroundColor(ContextCompat.getColor(context,R.color.customer_inactive))
        }
    }


    class ViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.name)
    }

}


typealias customerCallBack = (CustomerListData) -> Unit