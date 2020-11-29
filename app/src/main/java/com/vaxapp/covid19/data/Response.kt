package com.vaxapp.covid19.data

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data") val date: String,
    @SerializedName("comarcacodi") val countyCode: Int,
    @SerializedName("comarcadescripcio") val countyName: String,
    @SerializedName("municipicodi") val townCode: Int,
    @SerializedName("municipidescripcio") val townName: String,
    @SerializedName("sexecodi") val sexCode: Int,
    @SerializedName("sexedescripcio") val sexName: String,
    @SerializedName("resultatcoviddescripcio") val resultType: String,
    @SerializedName("numcasos") val casesNumber: Int
)
