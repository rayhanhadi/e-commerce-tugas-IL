package com.example.e_commerce.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Palet warna untuk dark mode
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFF1565C0),
    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF)
)

// Palet warna untuk light mode
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFF1565C0),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000)
)

// Shapes untuk aplikasi
val Shapes = androidx.compose.material3.Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)



@Composable
fun ECommerceTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
