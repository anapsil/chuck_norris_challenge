package dev.anapsil.chucknorris.facts.ui

import android.view.ViewGroup
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.anapsil.chucknorris.R
import dev.anapsil.chucknorris.common.ui.shareText
import dev.anapsil.chucknorris.facts.data.model.FactModel
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.Robolectric
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class FactsAdapterTest {
    private val activity = Robolectric.buildActivity(FactsActivity::class.java).create().get()
    private val parent = activity.findViewById<ViewGroup>(R.id.main_view)
    private lateinit var factsAdapter: FactsAdapter
    private val facts = listOf(
        FactModel(
            id = "XvhvQJ7JRhG_uSo7-bXLdA",
            url = "",
            value = "Chuck Norris was once caught without a ticket on the train. The inspector was fined \$110.",
            listOf()
        ),
        FactModel(
            id = "yWXM2uHiT5O31Qo19zRURw",
            url = "https://api.chucknorris.io/jokes/yWXM2uHiT5O31Qo19zRURw",
            value = "Chuck Norris speaks fluent Klingon, just so he can slaughter any nerd he hears speaking it.",
            listOf()
        )
    )

    @Before
    fun setup() {
        factsAdapter = FactsAdapter { activity.shareText(it) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check that adapter is created with no items`() {
        assertThat(factsAdapter.itemCount).isEqualTo(0)
    }

    @Test
    fun `check that updateFacts adds items to list correctly`() {
        factsAdapter.updateFacts(facts)

        assertThat(factsAdapter.itemCount).isEqualTo(2)
    }

    @Test
    fun `checks that onCreateViewHolder returns a FactsViewHolder instance`() {
        val holder = factsAdapter.onCreateViewHolder(parent, 0)

        assertThat(holder).isInstanceOf(FactsAdapter.FactsViewHolder::class.java)
    }

    @Test
    fun `checks that onBindViewHolder calls FactsViewHolder_bind method`() {
        factsAdapter.updateFacts(facts)
        val holder = mockk<FactsAdapter.FactsViewHolder>()
        every { holder.bind(any(), any()) } just Runs

        factsAdapter.onBindViewHolder(holder, 0)

        verify { holder.bind(any(), any()) }
    }

    @Test
    fun `checks that FactsViewHolder_bind setup views correctly`() {
        val callback = mockk<(String) -> Unit>()
        val holder = factsAdapter.onCreateViewHolder(parent, 0)
        every { callback("https://api.chucknorris.io/jokes/yWXM2uHiT5O31Qo19zRURw") } just Runs

        holder.bind(facts[1], callback)
        holder.binding.actionShare.performClick()

        verify { callback("https://api.chucknorris.io/jokes/yWXM2uHiT5O31Qo19zRURw") }
        assertThat(holder.binding.factText.text)
            .isEqualTo("Chuck Norris speaks fluent Klingon, just so he can slaughter any nerd he hears speaking it.")
        assertThat(holder.binding.factCategory.text).isEqualTo("")
    }
}