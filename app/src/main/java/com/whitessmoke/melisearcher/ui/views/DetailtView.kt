package com.whitessmoke.melisearcher.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.whitessmoke.melisearcher.databinding.ActivityDetailtViewBinding
import com.whitessmoke.melisearcher.ui.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

class DetailtView : AppCompatActivity() {
    lateinit var binding: ActivityDetailtViewBinding
    val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailtViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}