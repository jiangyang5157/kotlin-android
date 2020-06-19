package com.gmail.jiangyang5157.example_nfc

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.jiangyang5157.kotlin_kit.utils.HexUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
    }

    public override fun onResume() {
        super.onResume()
        nfcAdapter.enableReaderMode(
            this,
            this,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    public override fun onPause() {
        super.onPause()
        nfcAdapter.disableReaderMode(this)
    }

    override fun onTagDiscovered(tag: Tag?) {
        val isoDep = IsoDep.get(tag)
        isoDep.connect()
        val response = isoDep.transceive(
            HexUtils.hexToByte("00A4040007A0000002471001")
        )
        runOnUiThread {
            tv_info.append("\n# Card:\ntag=${tag} isoDep=${isoDep}\n transceive=${HexUtils.byteToHex(response)}")
        }
        isoDep.close()
    }
}
