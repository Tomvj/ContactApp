package com.example.contacttask.Screens

import ContactViewModel
import android.text.TextUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.contacttask.R
import com.example.contacttask.Responsemodel.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContactDashboard(viewModel: ContactViewModel, navHostController: NavHostController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    var isSearchExpanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        TopAppBar(
            title = {

                if (isSearchExpanded) {
                    // Show search field in the top bar when expanded
                    OutlinedTextField(
                        value = query,
                        onValueChange = {
                            query = it
                            // Trigger the search when the user types
                            if (query.length > 0) {
//                                mainViewModel.getSearch(it)
                            } else {
//                                mainViewModel.getPost()
                            }

                        },
                        placeholder = { Text("Search") },
                        textStyle = TextStyle(
                            fontSize = 12.sp // Set the desired text size
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(2.dp, 2.dp, 2.dp, 2.dp)
                    )
                } else {
                    Text(
                        text = "Contacts",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.primary_color),
                titleContentColor = colorResource(id = R.color.secondary_color)
            ),
            actions = {
                // IconButton for toggling search
                IconButton(onClick = {
                    // Toggle the search UI
                    isSearchExpanded = !isSearchExpanded

                    // If search is collapsed, clear the query
                    if (!isSearchExpanded) {
                        /* query = ""
                         mainViewModel.getPost()*/
                    }
                }) {
                    if (isSearchExpanded) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    } else {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                containerColor = colorResource(id = R.color.background_color),
                divider = {},
                indicator = { tabPositions ->
                    // Custom indicator using TabRowDefaults.PrimaryIndicator
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        height = 2.dp,
                        width = 150.dp,
                        color = colorResource(id = R.color.primary_color)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        modifier = Modifier.background(colorResource(id = R.color.background_color)),
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = colorResource(id = R.color.primary_color),
                        unselectedContentColor = colorResource(id = R.color.secondary_color),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.text,
                            fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
                        ) },
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (selectedTabIndex.value == 0) {
                    CustomContactScreen(
                        viewmodel = viewModel,
                        navHostController = navHostController )
                } else if (selectedTabIndex.value == 1) {
                    MyContactsScreen(viewmodel = viewModel,
                        navHostController = navHostController)
                }

            }
        }
    }
}

enum class HomeTabs(
    /*val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,*/
    val text: String
) {
    Shop(
        /* unselectedIcon = Icons.Outlined.ShoppingCart,
         selectedIcon = Icons.Filled.ShoppingCart,*/
        text = "Custom Contacts"
    ),
    Favourite(
        /*unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,*/
        text = "My Contacts"
    )
}

@Preview
@Composable
fun ScreenPreview() {
    ContactDashboard(viewModel = ContactViewModel(), rememberNavController())
}