package dev.havlicektomas.core.presentation.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = FinanceGreen40,
    background = FinanceWhite,
    surface = FinanceGray98,
    secondary = FinanceGreenGray40,
    tertiary = FinanceBlue40,
    error = FinanceRed40,
    primaryContainer = FinanceGreen90,
    onPrimary = FinanceWhite,
    onBackground = FinanceWhite,
    onSurface = FinanceGray10,
    onSurfaceVariant = FinanceGrayVariant30
)

val DarkColorScheme = darkColorScheme(
    primary = FinanceGreen80,
    background = FinanceBlack,
    surface = FinanceGray6,
    secondary = FinanceGreenGray80,
    tertiary = FinanceBlue80,
    error = FinanceRed80,
    primaryContainer = FinanceGreen30,
    onPrimary = FinanceBlack,
    onBackground = FinanceBlack,
    onSurface = FinanceGray90,
    onSurfaceVariant = FinanceGrayVariant90
)

@Composable
fun FinancemultimoduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}