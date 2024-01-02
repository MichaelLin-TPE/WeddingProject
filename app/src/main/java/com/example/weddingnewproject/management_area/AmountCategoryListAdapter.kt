package com.example.weddingnewproject.management_area

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CategoryData
import com.example.weddingnewproject.bean.CategoryListData
import java.text.DecimalFormat

class AmountCategoryListAdapter(private val dataList : ArrayList<CategoryListData>): RecyclerView.Adapter<AmountCategoryListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_amount_list_child_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = dataList[position].title
        holder.tvAmount.text = "$"+ formatNumberWithThousandsSeparator(dataList[position].totalAmount)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.title)
        val tvAmount = itemView.findViewById<TextView>(R.id.total_amount)
    }

    private fun formatNumberWithThousandsSeparator(number: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(number)
    }
}