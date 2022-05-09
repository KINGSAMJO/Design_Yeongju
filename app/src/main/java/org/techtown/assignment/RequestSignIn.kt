package org.techtown.assignment

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    @SerializedName("email")
    val id: String,
    val password: String,
)