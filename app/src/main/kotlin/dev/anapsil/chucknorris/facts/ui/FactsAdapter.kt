package dev.anapsil.chucknorris.facts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.anapsil.chucknorris.R
import dev.anapsil.chucknorris.databinding.ItemFactsBinding
import dev.anapsil.chucknorris.facts.data.model.JokeModel
import dev.anapsil.chucknorris.facts.ui.FactsAdapter.FactsViewHolder
import java.util.Locale

const val TEXT_LENGTH_LIMIT = 80
const val FONT_SIZE = 18F
const val LARGE_FONT_SIZE = 24F

class FactsAdapter(private val onShareClick: (String) -> Unit) : RecyclerView.Adapter<FactsViewHolder>() {
    private val items = mutableListOf<JokeModel>()

    fun addJoke(newJoke: JokeModel) {
        items.add(newJoke)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FactsViewHolder(ItemFactsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(items[position], onShareClick)
    }

    override fun getItemCount() = items.size

    class FactsViewHolder(val binding: ItemFactsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: JokeModel, onShareClick: (String) -> Unit) {
            with(binding) {
                factText.text = joke.value
                changeFontSize(factText)
                joke.categories.firstOrNull()?.let {
                    factCategory.text = it.toUpperCase(Locale.getDefault())
                } ?: run {
                    factCategory.text = this.root.context.getString(R.string.no_category_label)
                }
                actionShare.setOnClickListener {
                    onShareClick(joke.url)
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