package lingga.app.footballleague.ui.league

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import lingga.app.footballleague.R
import lingga.app.footballleague.model.League

class LeagueViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private var listLeague: MutableList<League> = mutableListOf()
    private var _league = MutableLiveData<List<League>>()
    val league: LiveData<List<League>>
        get() = _league

    init {
        _league.value = getListLeague(application)
    }

    @SuppressLint("Recycle")
    fun getListLeague(context: Context): List<League>? {
        val nama = context.resources.getStringArray(R.array.club_name)
        val image = context.resources.obtainTypedArray(R.array.club_image)
        val id = context.resources.getStringArray(R.array.club_id)
        listLeague.clear()
        for (i in id.indices) {
            listLeague.add(League(nama[i], image.getResourceId(i, 0), id[i]))
        }
        return listLeague
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}