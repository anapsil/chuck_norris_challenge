package dev.anapsil.chucknorris.facts.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.anapsil.chucknorris.common.ui.shareText
import dev.anapsil.chucknorris.common.ui.startActivityForResult
import dev.anapsil.chucknorris.databinding.ActivityFactsBinding
import dev.anapsil.chucknorris.search.ui.QUERY_TERM_KEY
import dev.anapsil.chucknorris.search.ui.SearchFactActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SEARCH_REQUEST_CODE = 0x400

class FactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFactsBinding
    private val viewModel: FactsViewModel by viewModel()
    private val factsAdapter = FactsAdapter { shareText(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        setupListeners()
        observeLiveData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val searchTerm = data?.extras?.get(QUERY_TERM_KEY)?.toString() ?: ""
            viewModel.searchFacts(searchTerm)
        }
    }

    private fun bindViews() {
        with(binding) {
            factsContent.factsList.adapter = factsAdapter
        }
    }

    private fun setupListeners() {
        with(binding) {
            toolbar.setOnMenuItemClickListener {
                startActivityForResult(SearchFactActivity::class.java, SEARCH_REQUEST_CODE)
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