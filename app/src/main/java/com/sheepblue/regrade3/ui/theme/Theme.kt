package com.sheepblue.regrade3.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    tertiary = PrincipalTextDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    tertiary = PrincipalTextLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight
)

@Composable
fun RegraDe3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val targetColorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val animatedColorScheme = targetColorScheme.copy(
        background = animateColorAsState(
            targetValue = targetColorScheme.background,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        ).value,
        surface = animateColorAsState(
            targetValue = targetColorScheme.surface,
            animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing)
        ).value,
        primary = animateColorAsState(
            targetValue = targetColorScheme.primary,
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
        ).value,
        onBackground = animateColorAsState(
            targetValue = targetColorScheme.onBackground,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ).value,
        onSurface = animateColorAsState(
            targetValue = targetColorScheme.onSurface,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ).value
    )

    MaterialTheme(
        colorScheme = animatedColorScheme,
        typography = Typography,
        content = content
    )
}
