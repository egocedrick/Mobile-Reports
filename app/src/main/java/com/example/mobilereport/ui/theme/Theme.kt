package com.example.mobilereport.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = GreenSecondary,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    tertiary = GreenTertiary,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = GrayBackground,
    onBackground = androidx.compose.ui.graphics.Color.Black,
    surface = SurfaceLight,
    onSurface = androidx.compose.ui.graphics.Color.Black,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFF424242),
    error = ErrorRed,
    onError = OnErrorRed,
    outline = OutlineLight
)

private val DarkColors = darkColorScheme(
    primary = GreenSecondary,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    secondary = GreenTertiary,
    onSecondary = androidx.compose.ui.graphics.Color.Black,
    tertiary = GreenPrimary,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = DarkBackground,
    onBackground = androidx.compose.ui.graphics.Color.White,
    surface = SurfaceDark,
    onSurface = androidx.compose.ui.graphics.Color.White,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFFBDBDBD),
    error = androidx.compose.ui.graphics.Color(0xFFCF6679),
    onError = androidx.compose.ui.graphics.Color.Black,
    outline = OutlineDark
)

@Composable
fun MobileReportTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalView.current.context
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}