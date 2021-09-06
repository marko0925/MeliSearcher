package com.whitessmoke.melisearcher.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.whitessmoke.melisearcher.business.resultsUseCase.ResultsUseCase
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.result.model.ResultModelPaging
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.experimental.CoroutineTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal class ResultsViewModelTest {
    private val DUMMY_TEXT = "patines"

    @InjectMocks
    lateinit var resultView: ResultsViewModel

    @Mock
    lateinit var paging: ResultModelPaging

    @Mock
    lateinit var useCase: ResultsUseCase

    @Mock
    lateinit var errors: Observer<String>

    @Mock
    lateinit var model: ResultModelResponse

    @Mock
    lateinit var observer: Observer<List<ModelProduct?>>

    @Mock
    lateinit var total: Observer<Int?>

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model.paging = paging
    }

    @org.junit.Test
    fun `probar ejecucion devolviendo el model en sendQuery()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {


            whenever(useCase.nextPage(anyString())).thenReturn(model)
            resultView.sendQuery(DUMMY_TEXT)
            resultView.resultsData.observeForever(observer)
            verify(observer, times(1)).onChanged(model.results)


        }

    @org.junit.Test
    fun `probar ejecucion devolviendo null en sendQuery()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            whenever(useCase.nextPage(anyString())).thenReturn(null)
            resultView.sendQuery(DUMMY_TEXT)
            resultView.errors.observeForever(errors)
            verify(errors, times(1)).onChanged("No fue posible obtener resultados")


        }

    @org.junit.Test
    fun `probar ejecucion devolviendo null en nextPage()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(useCase.nextPage(anyString())).thenReturn(null)
            resultView.nextPage(DUMMY_TEXT)
            resultView.errors.observeForever(errors)
            verify(errors, times(1)).onChanged("No fue posible obtener resultados")
        }

    @org.junit.Test
    fun `probar ejecucion lanzando error nextPage()`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            whenever(useCase.nextPage(anyString())).thenThrow(RuntimeException())
            resultView.nextPage(DUMMY_TEXT)
            resultView.errors.observeForever(errors)
            verify(
                errors,
                times(1)
            ).onChanged("¡Ops! algo ha ido mal, revisa tu conexión a internet e intentalo de nuevo mas tarde")

        }
}