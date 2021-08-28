package com.whitessmoke.melisearcher.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.adapters.result.ResultsAdapter
import com.whitessmoke.melisearcher.databinding.ActivityResultsViewBinding
import com.whitessmoke.melisearcher.ext.snackBar
import com.whitessmoke.melisearcher.ui.viewModels.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsView : AppCompatActivity() {

    lateinit var binding: ActivityResultsViewBinding
    lateinit var query: String
    val resultsViewModel: ResultsViewModel by viewModels()
    lateinit var adapter: ResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
        query = intent.getStringExtra("query").toString()
        observers()
        listeners()
        resultsViewModel.sendQuery(query)
        createAdapter()
    }

    private fun listeners() {

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = binding.rv.layoutManager?.childCount
                    val visibleItem =
                        (binding.rv.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if (visibleItemCount != null) {

                        if (resultsViewModel.isLoadingPaging.value == false && (visibleItemCount + visibleItem) >= total) {
                            resultsViewModel.nextPage(query)
                        }
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun createAdapter() {
        adapter = ResultsAdapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun observers() {
        resultsViewModel.isLoading.observe(this, {

            binding.progress.isVisible = it

        })
        resultsViewModel.errors.observe(this, {
            binding.root.snackBar(it)
        })
        resultsViewModel.resultsData.observe(this, {
            if (adapter.itemCount == 0) {
                binding.tv.isVisible = it?.isEmpty() == true
                binding.containerRV.isVisible = it?.isEmpty() == false
                if (it?.isEmpty() == true) {
                    binding.tv.text = "No se encontraron resultados"
                }
            }
            it?.let { it1 -> adapter.addItems(it1) }

        })
        resultsViewModel.total.observe(this, {

            binding.header.isVisible = true
            binding.total.text = "${it} resultados"

        })
        resultsViewModel.pages.observe(this, {
            binding.header.isVisible = true
            binding.paging.text = "${it} pagina"
        })
        resultsViewModel.isLoadingPaging.observe(this, {
            binding.progressPag.isVisible = it
        })
    }


}