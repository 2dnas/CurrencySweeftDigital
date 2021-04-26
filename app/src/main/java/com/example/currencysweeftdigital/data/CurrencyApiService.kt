package com.example.currencysweeftdigital.data

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "http://www.nbg.ge"


var retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

//ამ ინტერფეისს გამოიყენებს CurrencyApi Singleton
interface CurrencyApiService{
    @GET("rss.php")
     suspend fun getCurrencyAsString() : String
}

object CurrencyApi {
    val retrofitService: CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java)
    }
}
