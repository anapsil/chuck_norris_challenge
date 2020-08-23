package dev.anapsil.chucknorris.facts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.anapsil.chucknorris.databinding.ItemFactsBinding
import dev.anapsil.chucknorris.facts.data.model.FactModel
import dev.anapsil.chucknorris.facts.ui.FactsAdapter.FactsViewHolder

const val TEXT_LENGTH_LIMIT = 80
const val FONT_SIZE = 18F
const val LARGE_FONT_SIZE = 24F

class FactsAdapter(private val onShareClick: (String) -> Unit) : RecyclerView.Adapter<FactsViewHolder>() {
    private val items = mutableListOf<FactModel>()

    fun updateFacts(newItems: List<FactModel>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FactsViewHolder(ItemFactsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(items[position], onShareClick)
    }

    override fun getItemCount() = items.size

    class FactsViewHolder(val binding: ItemFactsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: FactModel, onShareClick: (String) -> Unit) {
            with(binding) {
                factText.text = fact.value
                changeFontSize(factText)
                factCategory.text = fact.categories.firstOrNull()
                actionShare.setOnClickListener {
                    onShareClick(fact.url)
                }
            }
        }

        private fun changeFontSize(textView: TextView) {
            when (textView.text.length) {
                in 1..TEXT_LENGTH_LIMIT -> textView.textSize = LARGE_FONT_SIZE
                else -> textView.textSize = FONT_SIZE
            }
        }
    }
}