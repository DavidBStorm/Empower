package com.empowertask.ui.detail

import com.empowertask.model.Beneficiary
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BENEFICIARY = "EXTRA_BENEFICIARY"

        fun newIntent(context: Context, beneficiary: Beneficiary): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_BENEFICIARY, beneficiary)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        setContentView(linearLayout)

        val beneficiary = intent.getParcelableExtra<Beneficiary>(EXTRA_BENEFICIARY)

        beneficiary?.let {
            linearLayout.addView(createTextView("Name: ${it.firstName} ${it.lastName}"))
            linearLayout.addView(createTextView("Type: ${it.beneType}"))
            linearLayout.addView(createTextView("Designation: ${if (it.designationCode == "P") "Primary" else "Contingent"}"))
            linearLayout.addView(createTextView("SSN: ${it.socialSecurityNumber}"))
            linearLayout.addView(createTextView("Date of Birth: ${formatDate(it.dateOfBirth)}"))
            linearLayout.addView(createTextView("Phone Number: ${it.phoneNumber}"))
            linearLayout.addView(createTextView("Address: ${it.beneficiaryAddress.firstLineMailing}, ${it.beneficiaryAddress.city}, ${it.beneficiaryAddress.stateCode}, ${it.beneficiaryAddress.zipCode}, ${it.beneficiaryAddress.country}"))
        }
    }

    private fun createTextView(text: String): TextView {
        return TextView(this).apply {
            setPadding(16, 0, 0, 8)
            this.text = text
        }
    }

    private fun formatDate(date: String): String {
        return if (date.length == 8) {
            "${date.substring(0, 2)}/${date.substring(2, 4)}/${date.substring(4)}"
        } else {
            date
        }
    }
}
