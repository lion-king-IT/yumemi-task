package com.reo.running.yumemitask.GithubAPI

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object APIClient {
    private val baseUrl: String = "https://api.github.com/"
    val retrofit: IApiType = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(IApiType::class.java)
}

interface IApiType {
    @GET("repos/googlesamples/android-architecture-components/contributors")
    suspend fun fetchRepository(): Response<List<Github>>
}