package com.nguyenhoangthanhan.stockmarketapp.di

import com.nguyenhoangthanhan.stockmarketapp.data.csv.CSVParser
import com.nguyenhoangthanhan.stockmarketapp.data.csv.CompanyListingsParser
import com.nguyenhoangthanhan.stockmarketapp.data.csv.IntradayInfoParser
import com.nguyenhoangthanhan.stockmarketapp.data.repository.StockRepositoryImpl
import com.nguyenhoangthanhan.stockmarketapp.domain.model.CompanyListing
import com.nguyenhoangthanhan.stockmarketapp.domain.model.IntradayInfo
import com.nguyenhoangthanhan.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun binStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}