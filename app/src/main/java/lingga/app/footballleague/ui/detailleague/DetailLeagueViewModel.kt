package lingga.app.footballleague.ui.detailleague

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.DetailLeague
import lingga.app.footballleague.network.LeagueApi

class DetailLeagueViewModel(val league: String) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _detail = MutableLiveData<DetailLeague>()
    val detail: LiveData<DetailLeague>
        get() = _detail

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    init {
        getLeagueApi()
    }

    private fun getLeagueApi() {
        coroutineScope.launch {
            val getDetailLeagueDeferred = LeagueApi.retrofitService.getDetailLeagueAsync(league)
            try {
                _status.value = View.VISIBLE
                _statusText.value = View.GONE
                val listLeague = getDetailLeagueDeferred.await()
                val list = listLeague.leagues
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _detail.value = list[0]
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _detail.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
