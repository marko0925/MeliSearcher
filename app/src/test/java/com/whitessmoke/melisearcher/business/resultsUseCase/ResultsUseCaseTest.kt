package com.whitessmoke.melisearcher.business.resultsUseCase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.data.result.network.ResultService
import com.whitessmoke.melisearcher.experimental.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
internal class ResultsUseCaseTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @InjectMocks
    lateinit var useCase: ResultsUseCase

    @Mock
    lateinit var model: ResultModelResponse

    @Mock
    lateinit var service: ResultService


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @AfterEach
    fun tearDown() {

    }

    @Test
    fun `probar obtener resultados correctamente`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(service.getResultsService(anyString(), anyInt())).thenReturn(model)
            val res = useCase.nextPage("patines")
            assertNotNull(res)
            assertEquals(model, res)
        }

    @Test
    fun `probar obtener error generico`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(service.getResultsService(anyString(), anyInt())).thenThrow(RuntimeException())
        try {
            val res = useCase.nextPage("iphone")

        } catch (e: java.lang.Exception) {
            assertTrue(e.message?.contains("¡Ops!") == true)
        }
    }


}