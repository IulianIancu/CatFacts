package com.iulian.iancu.catfacts.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("facts/random")
    suspend fun getCatFacts(
        @Query("amount") amount: Int = 10,
    ): Response<List<CatFact>>

    companion object {
        var retrofitService: CatService? = null
        fun getInstance(): CatService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://cat-fact.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CatService::class.java)
            }
            return retrofitService!!
        }
    }
}