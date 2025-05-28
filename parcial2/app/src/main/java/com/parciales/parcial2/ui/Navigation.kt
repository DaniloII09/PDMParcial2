package com.parciales.parcial2.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.parciales.parcial2.model.ProductRepository
import com.parciales.parcial2.ui.screen.*

@Composable
fun AppNavigation(navController: NavHostController) {
    val repository = remember { ProductRepository() }

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            HomeScreen(
                viewModel = viewModel(factory = HomeViewModelFactory(repository)),
                onProductClick = { productId ->
                    navController.navigate("detailProductScreen/$productId")
                },
                onCartClick = {
                    navController.navigate("cartScreen")
                }
            )
        }

        composable("detailProductScreen/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let { id ->
                ProductDetailScreen(
                    viewModel = viewModel(
                        factory = ProductDetailViewModelFactory(repository, id)
                    ),
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable("cartScreen") {
            CartScreen(
                viewModel = viewModel(factory = CartViewModelFactory(repository)),
                onBack = { navController.popBackStack() }
            )
        }
    }
}