package com.ruthvik.multifeature.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
    darkColorScheme(
        primary = DeepPurple,                  // Color(0xFF6750A4)
        onPrimary = White,                    // Color(0xFFFFFFFF)
        primaryContainer = Indigo,            // Color(0xFF21005D)
        onPrimaryContainer = Lavender,        // Color(0xFFEADDFF)

        secondary = DustyPurple,              // Color(0xFF625B71)
        onSecondary = White,                  // Color(0xFFFFFFFF)
        secondaryContainer = Charcoal,        // Color(0xFF1D192B)
        onSecondaryContainer = Lilac,         // Color(0xFFE8DEF8)

        tertiary = Mauve,                     // Color(0xFF7D5260)
        onTertiary = White,                   // Color(0xFFFFFFFF)
        tertiaryContainer = Maroon,           // Color(0xFF31111D)
        onTertiaryContainer = PinkBlush,      // Color(0xFFFFD8E4)

        background = DarkBackground,          // Color(0xFF1C1B1F)
        onBackground = LightGray,             // Color(0xFFE6E1E5)

        surface = DarkSurface,                // Color(0xFF1C1B1F)
        onSurface = LightGray,                // Color(0xFFE6E1E5)
        surfaceVariant = Slate,               // Color(0xFF49454F)
        onSurfaceVariant = Mist,              // Color(0xFFCAC4D0)

        error = Crimson,                      // Color(0xFFB3261E)
        onError = White,                      // Color(0xFFFFFFFF)
        errorContainer = DeepRed,             // Color(0xFF410E0B)
        onErrorContainer = Rose               // Color(0xFFF9DEDC)
    )


private val LightColorScheme = lightColorScheme(
    primary = DeepPurple,
    onPrimary = White,
    primaryContainer = Lavender,
    onPrimaryContainer = Indigo,
    secondary = DustyPurple,
    onSecondary = White,
    secondaryContainer = Lilac,
    onSecondaryContainer = Charcoal,
    tertiary = Mauve,
    onTertiary = White,
    tertiaryContainer = PinkBlush,
    onTertiaryContainer = Maroon,
    background = OffWhite,
    onBackground = DarkGray,
    surface = OffWhite,
    onSurface = DarkGray,
    error = Crimson,
    onError = White,
    errorContainer = Rose,
    onErrorContainer = DeepRed
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}