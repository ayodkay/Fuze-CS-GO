package com.example.fuze.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuze.beautify
import com.example.fuze.databinding.MatchBinding
import com.example.fuze.ifNull
import com.example.fuze.models.MatchResponse
import com.example.fuze.models.Opponent
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class MatchAdapter(var matches: List<MatchResponse>, var listeners: MatchListeners) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    class MatchViewHolder(private val binding: MatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(matchResponse: MatchResponse, listeners: MatchListeners) {
            val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

            if (matchResponse.begin_at != null) {
                val dateTime = LocalDateTime.parse(matchResponse.begin_at, formatter)
                binding.time = dateTime.beautify()
            } else {
                binding.time = matchResponse.status
            }

            if (!matchResponse.hasOpponent1() && !matchResponse.hasOpponent2()) {
                binding.opponent1 = Opponent(
                    acronym = null, id = 0, image_url = null, location = "", modified_at = "",
                    name = "TBH", slug = ""
                )

                binding.opponent2 = Opponent(
                    acronym = null, id = 0, image_url = null, location = "", modified_at = "",
                    name = "TBH", slug = ""
                )
            } else {
                binding.opponent1 = matchResponse.getOpponent1()?.opponent
                binding.opponent2 = matchResponse.getOpponent2()?.opponent

                binding.root.setOnClickListener { listeners.onClick(matchResponse) }
            }

            binding.leagueSerieName =
                "${matchResponse.league?.name.ifNull { "" }} ${matchResponse.serie?.name.ifNull { "" }}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches.getOrNull(position) ?: return, listeners)
    }
}