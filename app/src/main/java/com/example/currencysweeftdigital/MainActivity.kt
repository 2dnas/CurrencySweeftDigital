package com.example.currencysweeftdigital

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.currencysweeftdigital.adapter.CurrencyAdapter
import com.example.currencysweeftdigital.data.CurrencyApi
import com.example.currencysweeftdigital.data.Parser
import com.example.currencysweeftdigital.databinding.ActivityMainBinding
import com.example.currencysweeftdigital.model.Currency
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.StringReader


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //recyclerView ბაინდინგიდან რატომღაც არ მუშაობდა და recyclerView ს reference მაგიტომ ამოვიღე ამ მეთოდით
        recyclerView = findViewById(R.id.recycler_view)

        //ვინაიდან და რადგანაც getCurrencyAsString მეთოდი არის suspend ვიყენებ runBlocking ს
        runBlocking {
            val listResult = CurrencyApi.retrofitService.getCurrencyAsString()
            val parser = Parser()
            val currenciesString = parser.parse(listResult)
            val currencyListString = parser.parseCurrencyString(currenciesString)
            val mutableCurrency = currencyListString.toMutableList()
            mutableCurrency.removeAt(0)
            val currencyList = parser.parseEachEntry(mutableCurrency)
            recyclerView.adapter = CurrencyAdapter(currencyList)
        }


    }
}