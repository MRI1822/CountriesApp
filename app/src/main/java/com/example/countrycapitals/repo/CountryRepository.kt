package com.example.countrycapitals.repo

import com.example.countrycapitals.model.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
}
