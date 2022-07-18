package com.flagos.directory.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.flagos.directory.databinding.ActivityMainBinding
import com.flagos.directory.presentation.adapter.EmployeeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter = EmployeeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        viewModel.fetchEmployees()

        observeLiveData()
    }

    private fun observeLiveData() {
        with(viewModel) {
            onEmployeeDirectoryListRetrieved.observe(this@MainActivity) { adapter.submitList(it) }
            onLoading.observe(this@MainActivity) { loading -> binding.progress.visibility = if (loading) VISIBLE else GONE }
        }
    }
}