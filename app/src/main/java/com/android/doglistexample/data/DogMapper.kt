package com.android.doglistexample.data

import com.android.doglistexample.data.model.DogModel
import com.android.doglistexample.data.response.DogResponse

class DogMapper {

    fun getDogModelByDogResponse(dogResponse: DogResponse?): DogModel? {
        var result: DogModel? = null
        if (dogResponse != null) {
           result = DogModel(
                status = dogResponse.status == "success",
                images = dogResponse.images ?: emptyList(),
            )
        }

        return result
    }

}