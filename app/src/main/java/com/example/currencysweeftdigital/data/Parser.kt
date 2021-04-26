package com.example.currencysweeftdigital.data

import android.util.Log
import android.widget.TextView
import com.example.currencysweeftdigital.model.Currency
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

private const val NEXT_ENTRY = 12
private const val TAG = "sandro"
private const val ALL_CURRENCY = 43


//შემოსული იქსემელ სტრინგის პარსერი კლასი
class Parser {


    //პარსავს შემოსულ იქსემელს და აბრუნებს სტრინგად
    fun parse(xmlData: String): String {
        var inItem = false
        var textValue = ""
        var contentSting = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(xmlData.reader())
            var eventType = parser.eventType


            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name?.toLowerCase()
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "parsing $tagName")

                    }
                    XmlPullParser.TEXT -> textValue = parser.text
                    XmlPullParser.END_TAG -> {
                        when (tagName) {
                            "description" -> {
                                contentSting = textValue
                            }
                        }
                    }
                }
                eventType = parser.next()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


        return contentSting
    }

    //ვინაირად და რადგანაც შემოსული xml კიდევ საჭიროებს დალაგებას და მოწესრიგებას ეს მეთოდი პარსავს ოღონდ უკვე როგორც სტრინგს
    fun parseCurrencyString(currencyString: String): List<String> {
        val delimiterStartEntry = "<tr>"
        val delimiterEndEntry = "</tr>"
        val delimiterStartValue = "<td>"
        val delimiterEndValue = "</td>"
        return currencyString.split(delimiterStartEntry, delimiterEndEntry, delimiterStartValue, delimiterEndValue)

    }

    //parseEachEntry მეთოდი უკვე პარსავს თითოეულ Entry ს და აბრუნებს MutableList<Currency> Currency ობიექტებით
    fun parseEachEntry(list: MutableList<String>): MutableList<Currency> {
        val currencyList: MutableList<Currency> = mutableListOf()
        var currencyName = 3
        var currencyRate = 5
        var currencyAbbreviation = 1
        for (i in 1..ALL_CURRENCY) {
            val currency = Currency(
                list[currencyName],
                list[currencyAbbreviation],
                list[currencyRate]

            )
            currencyName += NEXT_ENTRY
            currencyRate += NEXT_ENTRY
            currencyAbbreviation += NEXT_ENTRY
            currencyList.add(currency)

        }
        return currencyList
    }
}