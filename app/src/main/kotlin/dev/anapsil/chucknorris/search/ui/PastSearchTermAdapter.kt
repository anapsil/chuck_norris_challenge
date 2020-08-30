package dev.anapsil.chucknorris.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.anapsil.chucknorris.databinding.ItemPastSearchTermBinding

class PastSearchTermAdapter(private val onTermClick: (String) -> Unit) : RecyclerView.Adapter<PastSearchTermAdapter.TermViewHolder>() {
    private val terms = mutableListOf<String>()

    fun updateTerms(newTerms: List<String>) {
        terms.addAll(newTerms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TermViewHolder(ItemPastSearchTermBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        holder.bind(terms[position], onTermClick)
    }

    override fun getItemCount() = terms.size

    class TermViewHolder(val binding: ItemPastSearchTermBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(term: String, onTermClick: (String) -> Unit) {
            with(binding.root) {
                text = term
                setOnClickListener { onTermClick(text.toString()) }
            }
        }
    }
}