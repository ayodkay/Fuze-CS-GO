package com.example.fuze.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuze.databinding.Opponent1detailsBinding
import com.example.fuze.models.Player

class Opponent1PlayersAdapter(var players: List<Player>) :
    RecyclerView.Adapter<Opponent1PlayersAdapter.MatchDetailsViewHolder>() {

    class MatchDetailsViewHolder(private val binding: Opponent1detailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.player = player
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Opponent1detailsBinding.inflate(inflater, parent, false)
        return MatchDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: MatchDetailsViewHolder, position: Int) {
        holder.bind(players.getOrNull(position) ?: return)
    }
}