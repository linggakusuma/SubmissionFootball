package lingga.app.footballleague.ui.detailmatch

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.db.database
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.model.Favorites
import lingga.app.footballleague.network.LeagueApi
import lingga.app.footballleague.utils.CoroutineContextProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMatchViewModel(
    val id: String, val context: Context?,
    contextDispather: CoroutineContextProvider = CoroutineContextProvider()
) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + contextDispather.main)

    private var _detailMatch = MutableLiveData<Event>()
    val detailMatch: LiveData<Event>
        get() = _detailMatch

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    private var _statusImage = MutableLiveData<Int>()
    val statusImage: LiveData<Int>
        get() = _statusImage

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {
        getDetailMatch()
        favoriteState()
    }

    fun getDetailMatch() {
        coroutineScope.launch {
            val getDetailMatchDeferred = LeagueApi.retrofitService.getDetailMatchAsync(id)
            try {
                _status.value = View.VISIBLE
                _statusText.value = View.GONE
                _statusImage.value = View.GONE
                val listDetail = getDetailMatchDeferred.await()
                val list = listDetail.events
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _statusImage.value = View.VISIBLE
                list.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }
                _detailMatch.value = list[0]
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _statusImage.value = View.VISIBLE
                _detailMatch.value = null
            }
        }
    }

    private fun favoriteState() {
        context?.database?.use {
            val favorite = select(Favorites.TABLE_FAVORITE).whereArgs(
                "(ID_EVENT = {id})", "id" to id
            ).parseList(classParser<Favorites>())
            _isFavorite.value = favorite.isNotEmpty()
        }
    }

    fun addToFavorite(typeMatch: String) {
        try {
            context?.database?.use {
                insert(
                    Favorites.TABLE_FAVORITE,
                    Favorites.ID_EVENT to _detailMatch.value?.idEvent,
                    Favorites.STR_EVENT to _detailMatch.value?.strEvent,
                    Favorites.DATE_EVENT to _detailMatch.value?.dateEvent,
                    Favorites.STR_TIME_LOCAL to _detailMatch.value?.strTimeLocal,
                    Favorites.HOME_TEAM_BADGE to _detailMatch.value?.homeTeamBadge,
                    Favorites.STR_HOME_TEAM to _detailMatch.value?.strHomeTeam,
                    Favorites.AWAY_TEAM_BADGE to _detailMatch.value?.awayTeamBadge,
                    Favorites.STR_AWAY_TEAM to _detailMatch.value?.strAwayTeam,
                    Favorites.INT_HOME_SCORE to _detailMatch.value?.intHomeScore,
                    Favorites.INT_AWAY_SCORE to _detailMatch.value?.intAwayScore,
                    Favorites.TYPE_MATCH to typeMatch
                )
            }
        } catch (e: SQLiteConstraintException) {
            context?.toast(e.localizedMessage)?.show()
        }
    }

    fun removeFavorite() {
        try {
            context?.database?.use {
                delete(
                    Favorites.TABLE_FAVORITE,
                    "(ID_EVENT = {id})",
                    "id" to id
                )
            }
        } catch (e: SQLiteConstraintException) {
            context?.toast(e.localizedMessage)?.show()
        }
    }
}
