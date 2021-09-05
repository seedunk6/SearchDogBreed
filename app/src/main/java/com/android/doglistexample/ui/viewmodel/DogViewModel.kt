package com.android.doglistexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.doglistexample.R
import com.android.doglistexample.data.model.DogModel
import com.android.doglistexample.domain.GetDogListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getDogList: GetDogListUseCase,
    ): ViewModel() {

    val dogModel = MutableLiveData<DogModel>()
    val isLoading = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<Int>()

    fun getData(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            var result: DogModel? = null

            try {
                result = getDogList(query)
            } catch (e: Exception){
                isLoading.postValue(false)
                showToast.postValue(R.string.connection_error)
            }

            result?.let {

                if (it.status){
                    dogModel.postValue(it)
                    isLoading.postValue(false)
                } else {
                    dogModel.postValue(it)
                    isLoading.postValue(false)
                    showToast.postValue(R.string.no_results_found)
                }

            }
        }
    }
}