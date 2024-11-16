package com.example.e_commerce

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tentang Saya", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF33576E))
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Gambar
                Image(
                    painter = painterResource(id = R.drawable.profile), // Ubah sesuai dengan gambar yang dimiliki
                    contentDescription = "Foto Profil",
                    modifier = Modifier.size(100.dp)
                )

                // Nama
                Text(
                    text = "Nama Anda",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Email
                Text(
                    text = "emailanda@domain.com",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Asal Perguruan Tinggi
                Text(
                    text = "Asal Perguruan Tinggi: Universitas Cokroaminoto Palopo",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                // Jurusan
                Text(
                    text = "Jurusan: Teknik Informatika",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewAboutPage() {
}
