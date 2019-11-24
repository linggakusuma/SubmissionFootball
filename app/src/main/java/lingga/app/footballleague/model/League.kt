package lingga.app.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val strLeague: String?,
    val image: Int?,
    val idLeague: String
) : Parcelable
