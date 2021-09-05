package com.android.doglistexample.data.network

import com.android.doglistexample.data.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogApiClient {
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogResponse?>
}