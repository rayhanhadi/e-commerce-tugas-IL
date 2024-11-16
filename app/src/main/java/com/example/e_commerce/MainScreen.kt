package com.example.e_commerce

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

data class Item(
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val imageResId: Int
)

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RyzKickShop", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF33576E))
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomePage(navController) }
            composable(
                "detail/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
                DetailPage(itemId = itemId, navController = navController)
            }
            composable("cart") { CartPage(navController) }
            composable("about") { AboutPage() }
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Cart",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Cart") },
            selected = currentRoute == "cart",
            onClick = {
                navController.navigate("cart") {
                    popUpTo("home")
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ix_about),
                    contentDescription = "About",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("About") },
            selected = currentRoute == "about",
            onClick = {
                navController.navigate("about") {
                    popUpTo("home")
                }
            }
        )
    }
}

@Composable
fun HomePage(navController: NavController) {
    val horizontalItems = remember {
        List(15) { index ->
            Item(
                id = index,
                title = "Produk Popular ${index + 1}",
                price = "Rp ${(index + 1) * 50000}",
                description = "Deskripsi lengkap untuk Produk Popular ${index + 1}",
                imageResId = when (index % 5) {
                    0 -> R.drawable.adidas1
                    1 -> R.drawable.sneakers4
                    2 -> R.drawable.sneakears2
                    3 -> R.drawable.sneakears3
                    else -> R.drawable.sneakers5
                }
            )
        }
    }

    val verticalItems = remember {
        List(15) { index ->
            Item(
                id = index + 15,
                title = "Produk Rekomendasi ${index + 1}",
                price = "Rp ${(index + 1) * 75000}",
                description = "Deskripsi lengkap untuk Produk Rekomendasi ${index + 1}",
                imageResId = when (index % 5) {
                    0 -> R.drawable.sneakers6
                    1 -> R.drawable.sneakers7
                    2 -> R.drawable.sneakers8
                    3 -> R.drawable.sneakers9
                    else -> R.drawable.sneakers10
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Produk Popular",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(horizontalItems) { item ->
                HorizontalItemCard(
                    item = item,
                    onItemClick = { navController.navigate("detail/${item.id}") }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Rekomendasi Untukmu",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(800.dp)
        ) {
            items(verticalItems) { item ->
                VerticalItemCard(
                    item = item,
                    onItemClick = { navController.navigate("detail/${item.id}") }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalItemCard(item: Item, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalItemCard(item: Item, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onItemClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CartPage(navController: NavHostController) {
    val cartItems = remember {
        mutableStateListOf(
            Item(1, "Sneakers 1", "Rp 500,000", "High-quality sneakers.", R.drawable.sneakers8),
            Item(2, "Sneakers 2", "Rp 700,000", "Comfortable and stylish.", R.drawable.sneakears2),
            Item(3, "Sneakers 3", "Rp 600,000", "Durable sneakers for daily use.", R.drawable.sneakers19)
        )
    }

    val totalPrice = cartItems.sumOf { it.price.replace("Rp ", "").replace(",", "").toInt() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Keranjang Belanja",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Keranjang Anda Kosong",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { item ->
                    CartItemCard(
                        item = item,
                        onRemoveItem = { cartItems.remove(item) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Harga: Rp ${String.format("%,d", totalPrice)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    onClick = { /* Tambahkan logika untuk checkout */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Checkout", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: Item, onRemoveItem: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(onClick = onRemoveItem) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Remove Item",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(navController: NavHostController, cartItems: List<Item>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Keranjang Belanja", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) { // Navigasi kembali
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF33576E))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (cartItems.isEmpty()) {
                // Jika keranjang kosong
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Keranjang Anda Kosong",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                // Jika ada item di keranjang
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartItems) { item ->
                        CartItemCard(
                            item = item,
                            onRemoveItem = TODO()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val totalPrice = cartItems.sumOf { it.price.replace("Rp ", "").replace(",", "").toInt() }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Harga: Rp ${String.format("%,d", totalPrice)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Button(
                        onClick = { /* Logika untuk checkout */ },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "Checkout", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}



@Composable
fun AboutPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan gambar profil
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan nama
        Text(
            text = "Muhammad Rayhan Hadinugraha",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Menampilkan email
        Text(
            text = "Rayhanhadi@gmail.com", // Ganti dengan email Anda
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Universitas Cokroaminoto Palopo", // Ganti dengan nama perguruan tinggi
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Jurusan: Teknik Informatika",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

