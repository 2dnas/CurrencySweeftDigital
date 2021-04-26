package com.example.currencysweeftdigital.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencysweeftdigital.R
import com.example.currencysweeftdigital.model.Currency


//recyclerView - ს ადაპტერი რომელსაც გადაეცემა პარამეტრათ ლისთი Currency ობიექტებით
class CurrencyAdapter(private val dataset : List<Currency>) :RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        val currencyName : TextView = view.findViewById(R.id.currency_name)
        val currencyRate : TextView = view.findViewById(R.id.currency_rate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CurrencyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = dataset[position]
        holder.currencyName.text = "ვალუტა : ${item.currencyName}(${item.currencyAbbreviation})"
        holder.currencyRate.text = "ღირებულება -> ${item.currencyRate}"
    }

    override fun getItemCount(): Int = dataset.size
}