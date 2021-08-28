package com.whitessmoke.melisearcher.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitessmoke.melisearcher.business.resultsUseCase.ResultsUseCase
import com.whitessmoke.melisearcher.data.result.model.ResultModelProduct
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.ext.isNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    val resultsUseCase: ResultsUseCase
) : ViewModel() {
    var pages = MutableLiveData<Int>(1)
    var isLoading = MutableLiveData<Boolean>()
    var resultsData = MutableLiveData<List<ResultModelProduct>>()
    var errors = MutableLiveData<String>()
    var total = MutableLiveData<Int>()
    var isLoadingPaging = MutableLiveData<Boolean>(false)

    fun sendQuery(str: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val results: ResultModelResponse? = resultsUseCase.nextPage(str)

            if (!results.isNull()) {
                resultsData.postValue(results?.results ?: emptyList())
                total.postValue(results?.paging?.total)
                pages.postValue(1)
            } else {
                errors.postValue("No fue posible obtener resultados")
            }
            isLoading.postValue(false)
        }
    }

    fun increasePage() {
        pages.postValue(pages.value?.plus(1))
    }

    fun nextPage(query: String) {
        viewModelScope.launch {
            isLoadingPaging.postValue(true)

            delay(4000)
            val results: ResultModelResponse? = resultsUseCase.nextPage(query)
            if (!results.isNull()) {
                resultsData.postValue(results?.results ?: emptyList())
                increasePage()
            } else {
                errors.postValue("No fue posible obtener resultados")
            }
            isLoadingPaging.postValue(false)
        }
    }
}