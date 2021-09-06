package com.whitessmoke.melisearcher.business.detailUseCase

import com.nhaarman.mockitokotlin2.whenever
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.detail.model.DetailModelDescription
import com.whitessmoke.melisearcher.data.detail.network.DetailService
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.data.result.network.ResultService
import com.whitessmoke.melisearcher.experimental.CoroutineTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

@RunWith(MockitoJUnitRunner::class)
internal class DetailUseCaseTest {
    private val MCO = "UN_MCO_CUALQUIERA"

    @InjectMocks
    lateinit var useCase: DetailUseCase

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var model: ModelProduct

    @Mock
    lateinit var description: DetailModelDescription

    @Mock
    lateinit var service: DetailService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @org.junit.Test
    fun `probar obtener los detalles de un producto`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(service.getDescription(anyString())).thenReturn(description)
            whenever(service.getDetail(anyString())).thenReturn(model)

            val resp = useCase.getDetails(MCO)
            assertNotNull(resp)
            assertEquals(resp, model)
        }

}