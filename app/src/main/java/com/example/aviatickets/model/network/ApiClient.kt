package com.example.aviatickets.model.network

import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.entity.OfferDeserializer
import com.example.aviatickets.model.entity.SnakeCaseNamingStrategy
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://my-json-server.typicode.com/estharossa/fake-api-demo/offer_list/"

    val apiService: ApiService by lazy {
        val gson = GsonBuilder()
            .setFieldNamingStrategy(SnakeCaseNamingStrategy)
            .registerTypeAdapter(Offer::class.java, OfferDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(ApiService::class.java)
    }
}