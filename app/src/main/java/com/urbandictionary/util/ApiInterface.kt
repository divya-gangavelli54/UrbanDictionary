package com.urbandictionary.util

import com.urbandictionary.entities.Dictionary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    /**
     * @param term word to search
     * @return list of dictionary from API
     */
    @Headers("X-RapidAPI-Key: " + Constant.API_KEY)
    @GET("/define")
    fun getList(
        @Query("term") term: String
    ): Call<Dictionary>
}