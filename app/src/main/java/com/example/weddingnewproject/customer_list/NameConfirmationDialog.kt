package com.example.weddingnewproject.customer_list

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import kotlin.math.roundToInt


class NameConfirmationDialog() : DialogFragment() {

    private var confirmationCallback: onNameConfirmationCallback? = null

    fun setOnNameConfirmationCallback(confirmationCallback: onNameConfirmationCallback){
        this.confirmationCallback = confirmationCallback
    }

    companion object {
        @JvmStatic
        fun newInstance(data: CustomerListData) =
            NameConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putSerializable("data", data)
                }
            }
    }


    private lateinit var tvName: TextView
    private lateinit var tvConfirm : TextView
    private lateinit var tvBack : TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_confirm_name_selection_layout, null)
        val dialog = Dialog(requireActivity())
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        initView(view)
        val configuration = resources.configuration
        val screenSize = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        val window = dialog.window
        val wlp = window!!.attributes
        when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> {
                // 大型螢幕，例如平板
                wlp.width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 280f, requireActivity().resources.displayMetrics).roundToInt()
                wlp.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 210f, requireActivity().resources.displayMetrics).roundToInt()
            }
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
                // 標準螢幕，大多數手機
                wlp.width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 280f, requireActivity().resources.displayMetrics).roundToInt()
                wlp.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 210f, requireActivity().resources.displayMetrics).roundToInt()
            }

            Configuration.SCREENLAYOUT_SIZE_SMALL -> {
                // 小型螢幕
                wlp.width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 280f, requireActivity().resources.displayMetrics).roundToInt()
                wlp.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 210f, requireActivity().resources.displayMetrics).roundToInt()
            }

            Configuration.SCREENLAYOUT_SIZE_XLARGE -> {
                // 特大螢幕，更大的平板
                wlp.width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 570f, requireActivity().resources.displayMetrics).roundToInt()
                wlp.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 420f, requireActivity().resources.displayMetrics).roundToInt()
            }
            else -> {
                // 未知或未指
                wlp.width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 280f, requireActivity().resources.displayMetrics).roundToInt()
                wlp.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 210f, requireActivity().resources.displayMetrics).roundToInt()
            }
        }
        window.attributes = wlp

        return dialog
    }

    private fun initView(view: View) {
        tvName = view.findViewById(R.id.name)
        tvConfirm = view.findViewById(R.id.confirm)
        tvBack = view.findViewById(R.id.back)
        val data = arguments?.getSerializable("data") as CustomerListData
        tvName.text = data.name
        tvConfirm.setOnClickListener {
            confirmationCallback?.invoke(data)
            dismiss()
        }
        tvBack.setOnClickListener {
            dismiss()
        }


    }

}

typealias onNameConfirmationCallback = (CustomerListData) ->Unit