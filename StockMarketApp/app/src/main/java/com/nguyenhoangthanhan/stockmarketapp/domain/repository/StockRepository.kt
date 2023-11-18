package com.nguyenhoangthanhan.stockmarketapp.domain.repository

import com.nguyenhoangthanhan.stockmarketapp.domain.model.CompanyListing
import com.nguyenhoangthanhan.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}