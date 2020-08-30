package dev.anapsil.chucknorris.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.anapsil.chucknorris.databinding.ItemCategoryBinding

class CategoriesAdapter(private val onCategoryClick: (String) -> Unit) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private val categories = mutableListOf<String>()

    fun updateCategories(newTerms: List<String>) {
        categories.addAll(newTerms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position], onCategoryClick)
    }

    override fun getItemCount() = categories.size

    class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(term: String, onCategoryClick: (String) -> Unit) {
            with(binding.root) {
                text = term
                setOnClickListener { onCategoryClick(text.toString()) }
            }
        }
    }
}