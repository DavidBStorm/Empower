package com.empowertask.repository

import com.empowertask.model.Beneficiary
import android.content.Context
import com.empowertask.R
import com.empowertask.model.Address

import org.json.JSONArray
import org.json.JSONObject

class BeneficiaryRepository(private val context: Context) {

    fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = context.resources.openRawResource(R.raw.beneficiaries)
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        val beneficiaryList = mutableListOf<Beneficiary>()

        for (i in 0 until jsonArray.length()) {
            val beneficiaryJson = jsonArray.getJSONObject(i)
            beneficiaryList.add(parseBeneficiary(beneficiaryJson))
        }

        return beneficiaryList
    }

    private fun parseBeneficiary(json: JSONObject): Beneficiary {
        val addressJson = json.getJSONObject("beneficiaryAddress")
        val address = Address(
            firstLineMailing = addressJson.getString("firstLineMailing"),
            scndLineMailing = addressJson.optString("scndLineMailing"),
            city = addressJson.getString("city"),
            zipCode = addressJson.getString("zipCode"),
            stateCode = addressJson.getString("stateCode"),
            country = addressJson.getString("country")
        )
        return Beneficiary(
            lastName = json.getString("lastName"),
            firstName = json.getString("firstName"),
            designationCode = json.getString("designationCode"),
            beneType = json.getString("beneType"),
            socialSecurityNumber = json.getString("socialSecurityNumber"),
            dateOfBirth = json.getString("dateOfBirth"),
            middleName = json.optString("middleName"),
            phoneNumber = json.getString("phoneNumber"),
            beneficiaryAddress = address
        )
    }
}
