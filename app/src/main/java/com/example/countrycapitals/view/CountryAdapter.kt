package com.example.countrycapitals.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countrycapitals.model.Country
import com.example.countrycapitals.R

class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)
        val codeTextView: TextView = itemView.findViewById(R.id.codeTextView)
        val regionTextView: TextView = itemView.findViewById(R.id.regionTextView)
    }

    override fun getItemCount(): Int {
        return if (countries.isEmpty()) 1 else countries.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (countries.isEmpty()) {
            // Name view is set to unavailable list as a placeholder
            holder.capitalTextView.visibility = View.GONE
            holder.codeTextView.visibility = View.GONE
            holder.regionTextView.visibility = View.GONE
        } else {
            val country = countries[position]
            holder.nameTextView.text = "${country.name}, "
            holder.capitalTextView.text = "${country.capital}"
            holder.codeTextView.text = "${country.code}, "
            holder.regionTextView.text ="${country.region}, "
        }
    }
}
