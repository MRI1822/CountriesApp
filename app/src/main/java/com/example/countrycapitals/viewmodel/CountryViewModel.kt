package com.example.countrycapitals.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycapitals.model.Country
import com.example.countrycapitals.repo.CountryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private var fetchJobCountries: Job? = null

    init {
        loadCountries()
    }

    override fun onCleared() {
        super.onCleared()
        cancelFetchCountries()
    }

    fun loadCountries() {
        fetchJobCountries = viewModelScope.launch {
            try {
                _countries.value = countryRepository.getCountries()
            } catch (e: Exception) {
                _error.value = "Error loading countries: ${e.message}"
            }
        }
    }

    private fun cancelFetchCountries() {
        fetchJobCountries?.cancel()
    }
}
