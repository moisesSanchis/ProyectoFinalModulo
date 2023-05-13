package com.msp.proyectomoises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.msp.proyectomoises.databinding.ActivityMainBinding
import com.msp.proyectomoises.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityScannerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initScanner()
    }

    private fun initScanner() {
       val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt(R.string.TxtMessageScan.toString())
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result:IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result!=null){
            if (result.contents==null){
                Toast.makeText(this, R.string.TxtCanceled, Toast.LENGTH_SHORT).show()
            }else{
                val codeBar:String = result.contents
                val intentResult = Intent(this, ResultActivity::class.java)
                intentResult.putExtra("codeBar", codeBar)
                startActivity(intentResult)

               // Toast.makeText(this, "El valor escaneado es ${result.contents}", Toast.LENGTH_SHORT).show()

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}