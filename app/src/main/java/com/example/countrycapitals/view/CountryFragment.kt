package com.example.countrycapitals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrycapitals.repo.CountryRepositoryImpl
import com.example.countrycapitals.R
import com.example.countrycapitals.viewmodel.CountryViewModel
import com.example.countrycapitals.viewmodel.CountryViewModelFactory

class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val countryRepository = CountryRepositoryImpl()

        val viewModelFactory = CountryViewModelFactory(countryRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CountryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CountryAdapter(emptyList())

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.countries.observe(viewLifecycleOwner, Observer {
            adapter = CountryAdapter(it)
            recyclerView.adapter = adapter
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })

        viewModel.loadCountries()
    }

    override fun onDestroyView() {
        // Remove observers to avoid leaks
        viewModel.countries.removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}
