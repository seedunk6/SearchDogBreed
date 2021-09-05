package com.android.doglistexample.data.network

import com.android.doglistexample.data.DogMapper
import com.android.doglistexample.data.model.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(
    private val api: DogApiClient,
    private val mapper: DogMapper
){
    suspend fun searchByName(query:String): DogModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getDogsByBreeds("$query/images")

            if (response.isSuccessful) {
                mapper.getDogModelByDogResponse(response.body())
            } else {
                DogModel(false, emptyList())
            }
        }

    }
}