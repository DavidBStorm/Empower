package com.empowertask.model
import android.os.Parcel
import android.os.Parcelable

data class Beneficiary(
    val lastName: String,
    val firstName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String?,
    val phoneNumber: String,
    val beneficiaryAddress: Address
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readParcelable(Address::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastName)
        parcel.writeString(firstName)
        parcel.writeString(designationCode)
        parcel.writeString(beneType)
        parcel.writeString(socialSecurityNumber)
        parcel.writeString(dateOfBirth)
        parcel.writeString(middleName)
        parcel.writeString(phoneNumber)
        parcel.writeParcelable(beneficiaryAddress, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Beneficiary> {
        override fun createFromParcel(parcel: Parcel): Beneficiary {
            return Beneficiary(parcel)
        }

        override fun newArray(size: Int): Array<Beneficiary?> {
            return arrayOfNulls(size)
        }
    }
}

