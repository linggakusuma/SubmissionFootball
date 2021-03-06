package lingga.app.footballleague.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lingga.app.footballleague.adapter.*
import lingga.app.footballleague.model.*

@BindingAdapter("list")
fun recyclerView(recyclerView: RecyclerView, data: List<League>) {
    val adapter = recyclerView.adapter as LeagueAdapter
    adapter.submitList(data)
}

@BindingAdapter("listEvent")
fun recyclerViewEvent(recyclerView: RecyclerView, data: List<Event>?) {
    val adapter = recyclerView.adapter as EventAdapter
    adapter.submitList(data)
}

@BindingAdapter("listFavorites")
fun recyclerViewFavorites(recyclerView: RecyclerView, data: List<Favorites>?) {
    val adapter = recyclerView.adapter as FavoritesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listStandings")
fun recyclerViewStandings(recyclerView: RecyclerView, data: List<Standings>?) {
    val adapter = recyclerView.adapter as StandingsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listTeams")
fun recyclerViewTeams(recyclerView: RecyclerView, data: List<Teams>?) {
    val adapter = recyclerView.adapter as TeamsAdapter
    adapter.submitList(data)
}

@BindingAdapter("image")
fun bindImage(imageView: ImageView, drawable: Int) {
    drawable.let {
        Glide.with(imageView.context)
            .load(drawable)
            .into(imageView)
    }
}

@BindingAdapter("imageApi")
fun bindImageApi(imageView: ImageView, url: String?) {
    url.let {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("infoGoals")
fun setInfoDetail(tv: TextView, data: String?) {
    data?.let {
        val text = data.split(";").joinToString("\n")
        tv.text = text
    }
}

@BindingAdapter("loadingProgress")
fun setLoadingProgress(progressBar: ProgressBar, status: Int?) {
    status?.let {
        progressBar.visibility = it
    }
}

@BindingAdapter("hideTextView")
fun setHideTextView(textView: TextView, status: Int?) {
    status?.let {
        textView.visibility = it
    }
}

@BindingAdapter("hideImage")
fun setHideImage(imageView: ImageView, status: Int?) {
    status?.let {
        imageView.visibility = it
    }
}

@BindingAdapter("hideCardView")
fun setHideCardView(cardView: CardView, status: Int?) {
    status?.let {
        cardView.visibility = it
    }
}