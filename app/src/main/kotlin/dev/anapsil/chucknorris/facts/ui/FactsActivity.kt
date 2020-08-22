package dev.anapsil.chucknorris.facts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.anapsil.chucknorris.databinding.ActivityFactsBinding

class FactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        setupListeners()
    }

    private fun bindViews() {
        with(binding) {
            factsContent.factsList.adapter = FactsAdapter()
        }
    }

    private fun setupListeners() {
        with(binding) {
            toolbar.setOnMenuItemClickListener {
                true
            }
        }
    }
}