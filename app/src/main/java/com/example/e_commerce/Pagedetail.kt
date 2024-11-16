package com.example.e_commerce

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    itemId: Int,
    navController: NavController
) {
    val item = remember {
        if (itemId < 15) {
            Item(
                id = itemId,
                title = "Produk Popular ${itemId + 1}",
                price = "Rp ${(itemId + 1) * 50000}",
                description = "Deskripsi lengkap untuk Produk Popular ${itemId + 1}. " +
                        "Temukan kenyamanan dan gaya dalam setiap langkah dengan sepatu kami yang dirancang khusus untuk Anda yang aktif dan penuh percaya diri. Dengan bahan berkualitas tinggi dan desain modern, sepatu ini cocok digunakan untuk berbagai aktivitas, dari santai hingga formal. Sol yang fleksibel dan ringan memberikan kenyamanan maksimal, sementara tampilannya yang trendi akan melengkapi gaya Anda sehari-hari. Tersedia dalam berbagai ukuran dan warna.",

                imageResId = when (itemId % 5) {
                    0 -> R.drawable.adidas1
                    1 -> R.drawable.sneakers4
                    2 -> R.drawable.sneakears2
                    3 -> R.drawable.sneakears3
                    else -> R.drawable.sneakers5
                }
            )
        } else {
            Item(
                id = itemId,
                title = "Produk Rekomendasi ${(itemId - 15) + 1}",
                price = "Rp ${(itemId - 15 + 1) * 75000}",
                description = "Deskripsi lengkap untuk Produk Rekomendasi ${(itemId - 15) + 1}. " +
                        "Produk ini adalah pilihan terbaik dengan harga yang sangat terjangkau.",
                imageResId = when ((itemId - 15) % 5) {
                    0 -> R.drawable.sneakers6
                    1 -> R.drawable.sneakers7
                    2 -> R.drawable.sneakers8
                    3 -> R.drawable.sneakers9
                    else -> R.drawable.sneakers10
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Deskripsi Produk",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Tambah ke Keranjang")
                    }

                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Beli Sekarang")
                    }
                }
            }
        }
    }
}

// Preview untuk DetailPage
@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    val navController = rememberNavController()
    DetailPage(itemId = 1, navController = navController)
}