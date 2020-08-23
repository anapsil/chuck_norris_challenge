package dev.anapsil.chucknorris.search.ui

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import dev.anapsil.chucknorris.databinding.ActivitySearchFactsBinding

const val QUERY_TERM_KEY = "query_term"

class SearchFactActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchFactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            inputSearch.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    setResult(RESULT_OK, Intent().apply { putExtra(QUERY_TERM_KEY, textView.text) })
                    finish()
                }
                false
            }
        }
    }
}