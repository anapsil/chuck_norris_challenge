package dev.anapsil.chucknorris.search.ui

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import dev.anapsil.chucknorris.databinding.ActivitySearchFactsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val QUERY_TERM_KEY = "query_term"

class SearchFactActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchFactsBinding
    private val viewModel: SearchFactViewModel by viewModel()
    private val termsAdapter = PastSearchTermAdapter { saveAndSendTerm(it) }
    private val categoriesAdapter = CategoriesAdapter { saveAndSendTerm(it, false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        setupListeners()
        observeLiveData()
        viewModel.loadAllTerms()
        viewModel.loadLocalCategories()
    }

    private fun bindViews() {
        viewModel.autoDisposable.bindTo(lifecycle)
        with(binding) {
            termsList.adapter = termsAdapter
            categoriesList.adapter = categoriesAdapter
        }
    }

    private fun setupListeners() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            inputSearch.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val term = textView.text.toString()
                    saveAndSendTerm(term)
                }
                false
            }
        }
    }

    private fun observeLiveData() {
        with(viewModel) {
            terms.observe(this@SearchFactActivity, {
                termsAdapter.updateTerms(it)
            })
            categories.observe(this@SearchFactActivity, {
                categoriesAdapter.updateCategories(it)
            })
        }
    }

    private fun saveAndSendTerm(term: String, save: Boolean = true) {
        if (save) viewModel.saveTerm(term)
        setResult(RESULT_OK, Intent().apply { putExtra(QUERY_TERM_KEY, term) })
        finish()
    }
}