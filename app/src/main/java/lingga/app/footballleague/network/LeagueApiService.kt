package lingga.app.footballleague.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import lingga.app.footballleague.BuildConfig
import lingga.app.footballleague.model.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BuildConfig.BASE_URL)
    .build()

interface LeagueApiService {
    @GET("lookupleague.php?")
    fun getDetailLeagueAsync(
        @Query("id") id: String?
    ): Deferred<ResponseLeague>

    @GET("eventsnextleague.php?")
    fun getNextMatchAsync(
        @Query("id") id: String?
    ): Deferred<ResponseEvent>

    @GET("eventspastleague.php?")
    fun getLastMatchAsync(
        @Query("id") id: String?
    ): Deferred<ResponseEvent>

    @GET("lookupteam.php?")
    fun getTeamAsync(
        @Query("id") id: String?
    ): Deferred<ResponseTeams>

    @GET("lookupevent.php")
    fun getDetailMatchAsync(
        @Query("id") id: String?
    ): Deferred<ResponseEvent>

    @GET("searchevents.php?")
    fun getSearchAsync(
        @Query("e") query: String?
    ): Deferred<ResponseSearchEvent>

    @GET("lookuptable.php?")
    fun getStandingsAsync(
        @Query("l") id: String?
    ): Deferred<ResponseStandings>

    @GET("lookup_all_teams.php?")
    fun getAllTeamsAsync(
        @Query("id") id: String?
    ): Deferred<ResponseTeams>

    @GET("searchteams.php?")
    fun getSearchTeamsAsync(
        @Query("t") query: String?
    ): Deferred<ResponseTeams>
}

object LeagueApi {
    val retrofitService: LeagueApiService by lazy { retrofit.create(LeagueApiService::class.java) }
}