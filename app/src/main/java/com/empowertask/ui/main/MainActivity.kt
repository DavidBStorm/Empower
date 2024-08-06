package com.empowertask.ui.main

import BeneficiaryViewModel
import BeneficiaryViewModelFactory
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.lifecycle.Observer
import com.empowertask.R
import com.empowertask.ui.detail.DetailActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val viewModel: BeneficiaryViewModel by viewModels {
        BeneficiaryViewModelFactory(applicationContext)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        scrollView.addView(linearLayout)
        setContentView(scrollView)


        viewModel.loadBeneficiaries()

        viewModel.beneficiaries.observe(this, Observer { beneficiaries ->
            beneficiaries?.forEach { beneficiary ->
                val textView = TextView(this).apply {
                    setPadding(16)
                    gravity = Gravity.CENTER_HORIZONTAL
                    text = "${beneficiary.firstName} ${beneficiary.lastName}, ${beneficiary.beneType}, ${
                        if (beneficiary.designationCode == "P") "Primary" else "Contingent"
                    }"
                    setOnClickListener {
                        viewModel.selectBeneficiary(beneficiary)
                        startActivity(DetailActivity.newIntent(this@MainActivity, beneficiary))
                    }
                }
                linearLayout.addView(textView)

            }
        })

    }
}