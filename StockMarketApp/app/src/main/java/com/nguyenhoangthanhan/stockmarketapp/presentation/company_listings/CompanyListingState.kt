package com.nguyenhoangthanhan.stockmarketapp.presentation.company_listings

import com.nguyenhoangthanhan.stockmarketapp.domain.model.CompanyListing

data class CompanyListingState (
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)