package lingga.app.footballleague.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import lingga.app.footballleague.BuildConfig
import lingga.app.footballleague.model.ResponseEvent
import lingga.app.footballleague.model.ResponseLeague
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

}

object LeagueApi {
    val retrofitService: LeagueApiService by lazy { retrofit.create(LeagueApiService::class.java) }
}