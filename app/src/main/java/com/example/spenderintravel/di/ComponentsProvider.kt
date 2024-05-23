package com.example.spenderintravel.di

import com.example.currency_converter.CurrencyConverterComponentProvider
import com.example.main.di.MainComponentProvider
import com.example.network.NetworkComponentProvider

interface ComponentsProvider :
    MainComponentProvider, CurrencyConverterComponentProvider, NetworkComponentProvider{}
