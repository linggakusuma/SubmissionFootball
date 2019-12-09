package lingga.app.footballleague.ui.search

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import lingga.app.footballleague.TestContextProvider
import lingga.app.footballleague.model.ResponseSearchEvent
import lingga.app.footballleague.network.LeagueApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiResponse: Deferred<ResponseSearchEvent>

    @Mock
    lateinit var application: Application

    private lateinit var viewModel: SearchViewModel

    @Mock
    lateinit var service: LeagueApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchViewModel("Arsenal", application, TestContextProvider())
    }

    @Test
    fun getSearchTest() {
        runBlocking {
            Mockito.`when`(service.getSearchAsync("Arsenal")).thenReturn(apiResponse)
            viewModel.getSearch()
        }
    }
}