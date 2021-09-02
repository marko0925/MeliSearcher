package com.whitessmoke.melisearcher.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.whitessmoke.melisearcher.business.detailUseCase.DetailUseCase
import com.whitessmoke.melisearcher.business.resultsUseCase.ResultsUseCase
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.experimental.CoroutineTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
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
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
internal class ResultsViewModelTest {
    private val MCO = "UN_MCO"

    @InjectMocks
    lateinit var resultView: ResultsViewModel

    @Mock
    lateinit var useCase: ResultsUseCase

    @Mock
    lateinit var errors: Observer<String>

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
    @After
    fun tearDown(){

    }

    /*@org.junit.Test
    fun `probar ejecucion devolviendo null en sendQuery()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            whenever(useCase.nextPage(anyString())).thenReturn(null)
            resultView.sendQuery("patines")
            resultView.errors.observeForever(errors)
            verify(errors, times(1)).onChanged("No fue posible obtener resultados")
        }

    @org.junit.Test
    fun `probar ejecucion devolviendo null en nextPage()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(useCase.nextPage(anyString())).thenReturn(null)
            resultView.nextPage("patines")
            resultView.errors.observeForever(errors)
            verify(errors, times(1)).onChanged("No fue posible obtener resultados")
        }*/

    @org.junit.Test
    fun `probar ejecucion lanzando error nextPage()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(useCase.nextPage(anyString())).thenThrow(RuntimeException())
            resultView.nextPage("patines")
            resultView.errors.observeForever(errors)
            verify(
                errors,
                times(1)
            ).onChanged("¡Ops! algo ha ido mal, intentalo de nuevo mas tarde")

        }
}