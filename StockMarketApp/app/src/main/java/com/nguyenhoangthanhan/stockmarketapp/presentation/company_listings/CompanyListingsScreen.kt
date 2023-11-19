@file:OptIn(ExperimentalMaterialApi::class)

package com.nguyenhoangthanhan.stockmarketapp.presentation.company_listings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nguyenhoangthanhan.stockmarketapp.presentation.destinations.CompanyInfoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun CompanyListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {

    val pullRefreshState = rememberPullRefreshState(viewModel.state.isRefreshing, {
        viewModel.onEvent(CompanyListingsEvent.Refresh)
    })

    val state = viewModel.state
    Log.d("Screen_TAG_CompanyListings", state.toString())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CompanyListingsEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search...",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            },
            maxLines = 1,
            singleLine = true
        )
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(state.companies.size) { i ->
                    val company = state.companies[i]
                    CompanyItem(
                        company = company,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer
                            )
                            .fillMaxWidth()
                            .clickable {
                                navigator.navigate(
                                    CompanyInfoScreenDestination(company.symbol)
                                )
                            }
                            .padding(16.dp)
                    )
                    if (i < state.companies.size) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            PullRefreshIndicator(
                viewModel.state.isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}