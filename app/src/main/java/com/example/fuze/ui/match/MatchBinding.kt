package com.example.fuze.ui.match

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fuze.R
import com.example.fuze.models.MatchResponse

@BindingAdapter("android:isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["android:matches", "android:listeners"])
fun RecyclerView.matches(matches: List<MatchResponse>?, listeners: MatchListeners) {
    if (matches != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MatchAdapter(matches, listeners)
        } else {
            (adapter as MatchAdapter).apply {
                this.matches = matches
                this.listeners = listeners
                notifyDataSetChanged()
            }
        }
    }
}

@BindingAdapter(value = ["android:glide", "android:placeholder"])
fun ImageView.glide(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).fallback(placeholder).into(this)
}


@BindingAdapter(value = ["android:time", "android:status"])
fun TextView.statusText(time: String?, status: String?) {
    if (status == "running") {
        backgroundTintList = AppCompatResources.getColorStateList(context, R.color.red)
        text = context.getString(R.string.now)
        isAllCaps = true
    } else {
        backgroundTintList = AppCompatResources.getColorStateList(context, R.color.gray)
        text = time
    }
}