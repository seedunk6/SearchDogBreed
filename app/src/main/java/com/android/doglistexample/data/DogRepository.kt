package com.android.doglistexample.data

import com.android.doglistexample.data.model.DogModel
import com.android.doglistexample.data.network.DogService
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val api: DogService){

    suspend fun getDogsList(query: String): DogModel? {
        return api.searchByName(query)
    }
}