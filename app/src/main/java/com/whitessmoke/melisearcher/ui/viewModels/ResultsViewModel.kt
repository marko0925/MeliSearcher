package com.whitessmoke.melisearcher.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitessmoke.melisearcher.business.resultsUseCase.ResultsUseCase
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.ext.isNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val resultsUseCase: ResultsUseCase
) : ViewModel() {
    var pages = MutableLiveData<Int>()
    var isLoading = MutableLiveData<Boolean>()
    var resultsData = MutableLiveData<List<ModelProduct>>()
    var errors = MutableLiveData<String>()
    var total = MutableLiveData<Int>()
    var isLoadingPaging = MutableLiveData<Boolean>(false)

    /**
     * Se envia apenas se carga la vista para consultar los productos que coinciden con el criterio
     * de busqueda y enviarlos a la vista a traves de los observers.
     * modifica el valor de [resultsData] con el contenido obtenido de la api
     * modifica el valor de [total] con el total de productos obtenidos en la busqueda
     * modifica el valor de [pages] con la pagina actual.
     * En caso de no obtener resultados modifica el valor de [errors] informando el hecho.
     * En caso de que el `nextPage()` genere excepción se modificara el valor de [errors] con el respectivo
     * mensaje
     * @param str query de la busqueda
     */
    fun sendQuery(str: String) {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                val results: ResultModelResponse? = resultsUseCase.nextPage(str)
                if (!results.isNull()) {
                    resultsData.postValue(results?.results ?: emptyList())
                    total.postValue(results?.paging?.total)
                    pages.postValue(1)
                } else {
                    errors.postValue("No fue posible obtener resultados")
                }
            } catch (e: Exception) {
                errors.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    /**
     * Se encarga de incrementar las paginas cada vez que se carga una nueva paginación y modifica el
     * valor de [pages] incrementando su valor en 1
     */
    fun increasePage() {
        pages.postValue(pages.value?.plus(1))
    }

    /**
     * Funcion encargada de consultar los productos que coinciden con la busqueda.
     * Similar a [sendQuery] con la variante de que se invoca desde la paginacion por tanto se
     * invoca el metodo [increasePage]
     */
    fun nextPage(query: String) {
        viewModelScope.launch {
            try {
                isLoadingPaging.postValue(true)
                val results = resultsUseCase.nextPage(query)
                if (!results.isNull()) {
                    resultsData.postValue(results?.results ?: emptyList())
                    increasePage()
                    results?.results?.size?.let { resultsUseCase.increaseOffset(it) }
                } else {
                    errors.postValue("No fue posible obtener resultados")
                }

            } catch (e: Exception) {
                errors.postValue(e.message)
            } finally {
                isLoadingPaging.postValue(false)
            }

        }
    }
}