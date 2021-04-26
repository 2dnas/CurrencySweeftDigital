package com.example.currencysweeftdigital.model

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException


//ვალუტის data კლასი რომელიც ინახავს სახელს, აბრევიატურას და ღირებულებას
data class Currency(
    val currencyName : String,
    val currencyAbbreviation  : String,
    val currencyRate : String

)
