package com.example.mapleleaves.utils

import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.example.mapleleaves.MapleLeavesApplication
import java.io.IOException

object AddressUtil {
    private const val TAG = "address"
    fun getAddress(latitude: Double, longitude: Double) :String{
        var addressList: List<Address>? = null
        var featureName=""
        val geocoder = Geocoder(MapleLeavesApplication.context)
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (addressList != null) {
            for (address in addressList) {
                Log.d(TAG, String.format("address: %s", address.toString()))
                Log.d(TAG,"地址：${address.featureName}")
                Log.d(TAG,"地址：${address.getAddressLine(0)}")
                featureName=address.featureName
            }
        }
        return featureName
    }
}