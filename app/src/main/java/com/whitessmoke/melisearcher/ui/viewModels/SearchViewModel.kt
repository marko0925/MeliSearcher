package com.whitessmoke.melisearcher.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    val callProducts = MutableLiveData<Boolean>()

    /**
     * Comprobaciones sobre el query
     * @param query texto a buscar en mercadolibre
     */
    fun submit(query: String) {
        if (query.isEmpty()) callProducts.postValue(false) else callProducts.postValue(true)

    }

}