package com.whitessmoke.melisearcher.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.whitessmoke.melisearcher.business.detailUseCase.DetailUseCase
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.experimental.CoroutineTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
internal class DetailViewModelTest {
    private val MCO = "UN_MCO"

    @InjectMocks
    lateinit var detailView: DetailViewModel

    @Mock
    lateinit var useCase: DetailUseCase

    @Mock
    lateinit var model: ModelProduct

    @Mock
    lateinit var observer: Observer<ModelProduct?>

    @Mock
    lateinit var observer2: Observer<String>

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @org.junit.Test
    fun `obtener detalles correctamente`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(useCase.getDetails(anyString())).thenReturn(model)
        detailView.getDetailsProduct(MCO)
        detailView.product.observeForever(observer)
        verify(observer, times(1)).onChanged(model)
    }

    @org.junit.Test
    fun `probar modelProducto viene null`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(useCase.getDetails(anyString())).thenReturn(null)
        detailView.getDetailsProduct(MCO)
        detailView.errors.observeForever(observer2)
        val error = "No fue posible obtener detalles del producto, intentalo de nuevo mas tarde"
        verify(observer2, times(1)).onChanged(error)
    }

    @org.junit.Test
    fun `probar fallo en tiempo de ejecucion`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(useCase.getDetails(anyString())).thenThrow(RuntimeException())
        detailView.getDetailsProduct(MCO)
        detailView.errors.observeForever(observer2)
        val error = "¡Ops! algo ha ido mal, intentalo de nuevo mas tarde"
        verify(observer2, times(1)).onChanged(error)
    }

}