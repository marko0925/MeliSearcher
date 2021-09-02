package com.whitessmoke.melisearcher.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.whitessmoke.melisearcher.databinding.ActivityMainBinding
import com.whitessmoke.melisearcher.ext.onEnterListener
import com.whitessmoke.melisearcher.ext.onTextListener
import com.whitessmoke.melisearcher.ext.snackBar
import com.whitessmoke.melisearcher.ext.toast
import com.whitessmoke.melisearcher.ui.viewModels.SearchViewModel
import com.whitessmoke.melisearcher.ui.viewModels.SearchViewModel_Factory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchView : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        listeners()
    }

    /**
     * Listeners de la vista
     */
    private fun listeners() {

        binding.sv.onEnterListener {
            searchViewModel.submit(binding.sv.text.trim().toString())
            //throw  RuntimeException("Test Crash"); // Force a crash
        }

    }

    /**
     * Observers que interactuan con la vista desde SearchViewModel
     */
    private fun observers() {
        searchViewModel.callProducts.observe(this, {
            if (!it) {
                binding.viewRoot.snackBar("No olvides escribir lo que desees buscar...")
            } else {
                val intent = Intent(this, ResultsView::class.java).apply {
                    putExtra("query", binding.sv.text.toString())
                }
                startActivity(intent)
                binding.sv.setText("")
            }
        })
    }

}