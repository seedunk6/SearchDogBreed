package com.android.doglistexample.domain

import com.android.doglistexample.data.DogRepository
import javax.inject.Inject

class GetDogListUseCase @Inject constructor(
    private val repository: DogRepository
){
    suspend operator fun invoke(query: String) = repository.getDogsList(query)
}