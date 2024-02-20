package com.hpcl_paytm.activity

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.View.VISIBLE
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hpcl_paytm.R


class SettlementDialog(var context: Activity) {
    lateinit var dialog: Dialog
    lateinit var title: TextView
    lateinit var processing: TextView
    lateinit var packetStatus: TextView
    lateinit var timerAndEndpoint: TextView
    lateinit var imageView: ImageView
    lateinit var image: ImageView
    lateinit var state : TextView

    init {
        dialog = Dialog(context, com.chaos.view.R.style.Theme_AppCompat_Dialog)
        dialog.setContentView(R.layout.settlement_dialog)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        setStyle()
        initView()
        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    }

    private fun setStyle() {
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initView() {
        title = dialog.findViewById<View>(R.id.title) as TextView
        processing = dialog.findViewById<View>(R.id.processing) as TextView
        packetStatus = dialog.findViewById<View>(R.id.packetStatus) as TextView
        timerAndEndpoint = dialog.findViewById<View>(R.id.timerAndEndpoint) as TextView
        imageView = dialog.findViewById(R.id.progressGif)
        image = dialog.findViewById(R.id.image)
        state = dialog.findViewById(R.id.status)
        Glide.with(context).load(R.mipmap.ic_loading).into(imageView)
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun isShowing():Boolean {
        return  dialog.isShowing
    }
    fun setTitle(titleString: String) {
        title.text = titleString
    }

    fun setProcessing() {
        processing.text = "Processing Transaction"
    }

    fun setpacketStatus(status: String) {
        packetStatus.text = status
    }

    fun settimerAndEndpoint(status: String) {
        timerAndEndpoint.text = status
    }

    fun setVisibilityForSettlementStatus() {
        state.visibility = View.VISIBLE
        state.textSize = 13.0f
    }

    fun show() {
        dialog.show()
    }

    fun onSuccess() {
        image.setVisibility(View.VISIBLE)
        image.setImageResource(R.mipmap.ic_app_success)
        timerAndEndpoint.visibility = View.GONE
        processing.visibility = View.GONE
        state.text = "Received"
    }

    fun onFailure(status: String?) {

        image.visibility = View.VISIBLE
        image.setImageResource(R.mipmap.ic_app_error)
        timerAndEndpoint.visibility = View.GONE
        state.visibility=View.VISIBLE
        state.text = status
    }

}