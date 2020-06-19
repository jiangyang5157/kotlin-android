package com.gmail.jiangyang5157.example_downloadmanager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter()
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        registerReceiver(downLoadFinishReceiver, intentFilter)

        btn_1.setOnClickListener {
            tv_info.text = null
            val request = DownloadManager.Request(
                Uri.parse("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-100x100.png")
            )
            request.setTitle("title")
            request.setDescription("description")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "file-name")
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "file-name")

            (getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(request)
        }
    }

    private val downLoadFinishReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val action = intent.action
                tv_info.text = action.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downLoadFinishReceiver)
    }
}
