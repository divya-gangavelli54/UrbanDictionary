package com.urbandictionary.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Dictionary : Serializable {

    @SerializedName("list")
    var dictionary: List<Information>? = null
}