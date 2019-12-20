package lingga.app.footballleague.ui.lastmatch

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.mock
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.model.ResponseEvent
import lingga.app.footballleague.network.LeagueApi
import lingga.app.footballleague.network.LeagueApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@Suppress("DeferredResultUnused")
class LastMatchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiResponse: Deferred<ResponseEvent>

    @Mock
    lateinit var application: Application

    private lateinit var viewModel: LastMatchViewModel
    private val observer: Observer<List<Event>> = mock()
    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiResponse = LeagueApi.retrofitService.getLastMatchAsync("4328")
        viewModel = LastMatchViewModel("4328", application, TestContextProvider())
        viewModel.eventLastMatch.observeForever(observer)
    }

    @Test
    fun getNextMatchTest() {
        runBlocking {
            Mockito.`when`(service.getLastMatchAsync("4328")).thenReturn(apiResponse)
            viewModel.getLastMatch()
            verify(observer, never()).onChanged(apiResponse.await().events)
        }
    }
}