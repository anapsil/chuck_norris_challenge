package dev.anapsil.chucknorris.facts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.anapsil.chucknorris.common.ui.shareText
import dev.anapsil.chucknorris.databinding.ActivityFactsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFactsBinding
    private val viewModel: FactsViewModel by viewModel()
    private val factsAdapter = FactsAdapter { shareText(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadFacts()
        bindViews()
        setupListeners()
        observeLiveData()
    }

    private fun bindViews() {
        with(binding) {
            factsContent.factsList.adapter = factsAdapter
        }
    }

    private fun setupListeners() {
        with(binding) {
            toolbar.setOnMenuItemClickListener {
                true
            }
        }
    }

    private fun observeLiveData() {
        with(viewModel) {
            facts.observe(this@FactsActivity, {
                if (it.isNotEmpty()) {
                    factsAdapter.updateFacts(it)
                    binding.contentPanel.displayedChild = 1
                }
            })
        }
    }
}