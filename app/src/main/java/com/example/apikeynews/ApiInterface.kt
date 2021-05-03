package com.example.apikeynews


import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @GET("api/id/covid19/hoaxes")
    fun getCEOs(): Call<ArrayList<CEOModel>>

}