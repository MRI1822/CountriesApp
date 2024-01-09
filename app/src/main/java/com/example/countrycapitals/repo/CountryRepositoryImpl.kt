package com.example.countrycapitals.repo

import com.example.countrycapitals.model.Country
import com.example.countrycapitals.service.CountryService
import com.example.countrycapitals.service.RetrofitClient

class CountryRepositoryImpl : CountryRepository {

    private val countryService: CountryService = RetrofitClient.countryService

    override suspend fun getCountries(): List<Country> {
        return countryService.getCountries()
    }
}
