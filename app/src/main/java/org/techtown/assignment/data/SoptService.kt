package org.techtown.assignment.data

import org.techtown.assignment.model.RequestSignIn
import org.techtown.assignment.model.RequestSignUp
import org.techtown.assignment.model.ResponseSignIn
import org.techtown.assignment.model.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
}