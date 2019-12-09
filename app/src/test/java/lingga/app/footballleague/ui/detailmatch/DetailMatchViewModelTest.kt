package lingga.app.footballleague.ui.detailmatch

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.model.ResponseEvent
import lingga.app.footballleague.network.LeagueApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiResponse: Deferred<ResponseEvent>
    @Mock
    private lateinit var context: Context

    private lateinit var viewModel: DetailMatchViewModel

    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMatchViewModel("602285", context.applicationContext, TestContextProvider())
    }

    @Test
    fun getDetailMatchTest() {
        runBlocking {
            Mockito.`when`(service.getDetailMatchAsync("602285")).thenReturn(apiResponse)
            viewModel.getDetailMatch()
        }
    }
}