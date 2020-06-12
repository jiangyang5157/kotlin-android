package com.gmail.jiangyang5157.example_biometric

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class FingerprintDialog(private val callback: Callback? = null) : DialogFragment() {

    companion object {
        const val TAG = "FingerprintDialog"
    }

    interface Callback {
        fun onDismiss()
    }

    private var tvInfo: TextView? = null

    fun setInfo(info: CharSequence) {
        tvInfo?.text = info
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(context).apply {
            text = "..."
            tvInfo = this
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callback?.onDismiss()
    }
}
