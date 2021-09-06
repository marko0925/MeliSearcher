package com.whitessmoke.melisearcher.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitessmoke.melisearcher.business.detailUseCase.DetailUseCase
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.ext.isNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var detailUseCase: DetailUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val errors = MutableLiveData<String>()
    val product = MutableLiveData<ModelProduct?>()

    /**
     * Metodo encargado de obtener los detalles de un producto y enviar los datos a la vista
     * a traves de los observers
     * Modifica el valor de [product] con los datos del producto
     * Modifica el valor de [errors] con un mensaje en caso de no obtener los datos del producto
     * En caso de obtener un error proveniente de `getDetails()` modifica el valor de [errors] con
     * el mensaje del error en cuestion
     * @param id codigo MCO del producto de mercadolibre
     */
    fun getDetailsProduct(id: String) {

        viewModelScope.launch {
            try {
                loading.postValue(true)
                val model = detailUseCase.getDetails(id)
                if (!model.isNull()) {
                    product.postValue(model)
                } else {
                    errors.postValue("No fue posible obtener detalles del producto, intentalo de nuevo mas tarde")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errors.postValue("¡Ops! algo ha ido mal, revisa tu conexión a internet e intentalo de nuevo mas tarde")
            } finally {
                loading.postValue(false)
            }

        }
    }
}