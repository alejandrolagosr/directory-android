package com.flagos.directory.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getColor
import com.flagos.directory.R
import com.flagos.directory.databinding.ActivityMainBinding
import com.flagos.directory.domain.model.EmployeeItem
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
        with(binding.swipeToRefresh) {
            val color = getColor(this@MainActivity, R.color.teal_200)
            setColorSchemeColors(color)
            setOnRefreshListener {
                viewModel.fetchEmployees()
            }
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
            is OnListRetrieved -> setSuccessfulState(state.list)
            is OnLoading -> setLoadingState(state.loading)
        }
    }

    private fun setLoadingState(loading: Boolean) {
        if (loading) {
            binding.progress.visibility = VISIBLE
            binding.textFailedMessage.visibility = GONE
        } else {
            binding.progress.visibility = GONE
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setErrorState(error: String?) {
        changeListVisibility(GONE)
        with(binding.textFailedMessage) {
            text = String.format(getString(R.string.text_error), error)
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_error, 0, 0)
            visibility = VISIBLE
        }
    }

    private fun setEmptyState() {
        changeListVisibility(GONE)
        with(binding.textFailedMessage) {
            text = getString(R.string.text_empty)
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_empty, 0, 0)
            visibility = VISIBLE
        }
    }

    private fun setSuccessfulState(list: List<EmployeeItem>) {
        changeListVisibility(VISIBLE)
        adapter.submitList(list)
    }

    private fun changeListVisibility(visibility: Int) {
        binding.recycler.visibility = visibility
    }
}