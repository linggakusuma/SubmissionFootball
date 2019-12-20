package lingga.app.footballleague.ui.search

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.mock
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.model.ResponseSearchEvent
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
class SearchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiResponse: Deferred<ResponseSearchEvent>

    @Mock
    lateinit var application: Application

    private lateinit var viewModel: SearchViewModel
    private val observer: Observer<List<Event>> = mock()
    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiResponse = LeagueApi.retrofitService.getSearchAsync("Arsenal")
        viewModel = SearchViewModel("Arsenal", application, TestContextProvider())
        viewModel.event.observeForever(observer)
    }

    @Test
    fun getSearchTest() {
        runBlocking {
            Mockito.`when`(service.getSearchAsync("Arsenal")).thenReturn(apiResponse)
            viewModel.getSearch()
            verify(observer, never()).onChanged(apiResponse.await().event)
        }
    }
}