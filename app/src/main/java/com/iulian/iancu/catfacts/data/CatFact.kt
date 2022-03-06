package com.iulian.iancu.catfacts.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CatFact(
    @Expose
    @SerializedName("text")
    val text: String
)