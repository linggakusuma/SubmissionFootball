@file:Suppress("IncorrectScope")

package lingga.app.footballleague.ui.detailleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.model.ResponseLeague
import lingga.app.footballleague.network.LeagueApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeagueViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiResponse: Deferred<ResponseLeague>

    private lateinit var viewModel: DetailLeagueViewModel

    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailLeagueViewModel("4328", TestContextProvider())
    }

    @Test
    fun getDetailLeague() {
        runBlocking {
            Mockito.`when`(service.getDetailLeagueAsync("4328")).thenReturn(apiResponse)
            viewModel.getLeagueApi()
        }
    }
}
