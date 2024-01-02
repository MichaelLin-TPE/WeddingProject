package com.example.weddingnewproject.management_area

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CategoryData
import java.text.DecimalFormat

class AmountCategoryAdapter(private val dataList : ArrayList<CategoryData>): RecyclerView.Adapter<AmountCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_amount_list_parent_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = dataList[position].title
        holder.tvAmount.text = "$"+ formatNumberWithThousandsSeparator(dataList[position].totalAmount)
        val adapter = AmountCategoryListAdapter(dataList[position].categoryList)
        holder.rvCategoryList.adapter = adapter
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.title)
        val tvAmount = itemView.findViewById<TextView>(R.id.total_amount)
        val rvCategoryList = itemView.findViewById<RecyclerView>(R.id.category_list)
    }

    fun formatNumberWithThousandsSeparator(number: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(number)
    }
}