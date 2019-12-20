package lingga.app.footballleague.ui.detailleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.mock
import lingga.app.footballleague.model.DetailLeague
import lingga.app.footballleague.model.ResponseLeague
import lingga.app.footballleague.network.LeagueApi
import lingga.app.footballleague.network.LeagueApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("DeferredResultUnused")
class DetailLeagueViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiResponse: Deferred<ResponseLeague>


    private lateinit var viewModel: DetailLeagueViewModel
    private val observer: Observer<DetailLeague> = mock()

    @Mock
    lateinit var service: LeagueApiService


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiResponse = LeagueApi.retrofitService.getDetailLeagueAsync("4328")
        viewModel = DetailLeagueViewModel("4328", TestContextProvider())
        viewModel.detail.observeForever(observer)
    }

    @Test
    fun getDetailLeagueTest() {
        runBlocking {
            `when`(service.getDetailLeagueAsync("4328")).thenReturn(
                apiResponse
            )
            viewModel.getLeagueApi()
            verify(observer, never()).onChanged(apiResponse.await().leagues[0])
        }
    }
}