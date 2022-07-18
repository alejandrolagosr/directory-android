package com.flagos.directory.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.flagos.directory.R
import com.flagos.directory.databinding.ActivityMainBinding
import com.flagos.directory.presentation.MainViewModel.EmployeeDirectoryState
import com.flagos.directory.presentation.MainViewModel.EmployeeDirectoryState.*
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

        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        binding.recycler.adapter = adapter
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchEmployees()
        }
    }

    private fun setUpObservers() {
        with(viewModel) {
            fetchEmployees()
            onEmployeeDirectoryState.observe(this@MainActivity) { state -> setUiState(state) }
        }
    }

    private fun setUiState(state: EmployeeDirectoryState) {
        when (state) {
            OnEmptyList -> setEmptyState()
            is OnError -> setErrorState(state.error)
            is OnListRetrieved -> adapter.submitList(state.list)
            is OnLoading -> setLoadingState(state.loading)
        }
    }

    private fun setLoadingState(loading: Boolean) {
        if (loading) {
            binding.progress.visibility = VISIBLE
            binding.textFailedMessage.visibility = GONE
        } else {
            binding.swipeToRefresh.isRefreshing = false
            binding.progress.visibility = GONE
        }
    }

    private fun setErrorState(error: String?) {
        with(binding.textFailedMessage) {
            text = String.format(getString(R.string.text_error), error)
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_error, 0, 0)
            visibility = VISIBLE
        }
    }

    private fun setEmptyState() {
        with(binding.textFailedMessage) {
            text = getString(R.string.text_empty)
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_empty, 0, 0)
            visibility = VISIBLE
        }
    }
}