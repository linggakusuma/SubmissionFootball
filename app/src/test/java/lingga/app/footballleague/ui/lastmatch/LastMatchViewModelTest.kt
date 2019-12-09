package lingga.app.footballleague.ui.lastmatch

import android.app.Application
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

class LastMatchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiResponse: Deferred<ResponseEvent>

    @Mock
    lateinit var application: Application

    private lateinit var viewModel: LastMatchViewModel

    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LastMatchViewModel("602285", application, TestContextProvider())
    }

    @Test
    fun getLastMatchTest() {
        runBlocking {
            Mockito.`when`(service.getLastMatchAsync("602285")).thenReturn(apiResponse)
            viewModel.getLastMatch()
        }
    }
}