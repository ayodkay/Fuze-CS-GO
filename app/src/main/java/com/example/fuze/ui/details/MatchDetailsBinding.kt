package com.example.fuze.ui.details

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuze.models.Player

@BindingAdapter("android:opponent1Players")
fun RecyclerView.opponent1Players(players: List<Player>?) {
    if (players != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = Opponent1PlayersAdapter(players)
        } else {
            (adapter as Opponent1PlayersAdapter).apply {
                this.players = players
                notifyDataSetChanged()
            }
        }
    }
}


@BindingAdapter("android:opponent2Players")
fun RecyclerView.opponent2Players(players: List<Player>?) {
    if (players != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = Opponent2PlayersAdapter(players)
        } else {
            (adapter as Opponent2PlayersAdapter).apply {
                this.players = players
                notifyDataSetChanged()
            }
        }
    }
}