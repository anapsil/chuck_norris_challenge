package dev.anapsil.chucknorris.facts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.anapsil.chucknorris.databinding.ItemFactsBinding
import dev.anapsil.chucknorris.facts.data.model.FactModel
import dev.anapsil.chucknorris.facts.ui.FactsAdapter.FactsViewHolder

class FactsAdapter : RecyclerView.Adapter<FactsViewHolder>() {
    private val items = mutableListOf<FactModel>()

    fun updateFacts(newItems: MutableList<FactModel>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FactsViewHolder(ItemFactsBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class FactsViewHolder(private val binding: ItemFactsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: FactModel) {
            binding.factText.text = fact.value
        }
    }
}