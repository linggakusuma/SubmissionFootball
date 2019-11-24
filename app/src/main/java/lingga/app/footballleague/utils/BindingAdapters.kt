package lingga.app.footballleague.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lingga.app.footballleague.adapter.LeagueAdapter
import lingga.app.footballleague.model.League

@BindingAdapter("list")
fun recyclerView(recyclerView: RecyclerView, data: List<League>) {
    val adapter = recyclerView.adapter as LeagueAdapter
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