package lingga.app.footballleague.ui.detailmatch

import android.content.Context
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
class DetailMatchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiResponse: Deferred<ResponseEvent>
    @Mock
    private lateinit var context: Context

    private lateinit var viewModel: DetailMatchViewModel
    private val observer: Observer<Event> = mock()
    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiResponse = LeagueApi.retrofitService.getDetailMatchAsync("602285")
        viewModel =
            DetailMatchViewModel("602285", context.applicationContext, TestContextProvider())
        viewModel.detailMatch.observeForever(observer)
    }

    @Test
    fun getDetailMatchTest() {
        runBlocking {
            Mockito.`when`(service.getDetailMatchAsync("602285")).thenReturn(apiResponse)
            viewModel.getDetailMatch()
            verify(observer, never()).onChanged(apiResponse.await().events[0])
        }
    }
}