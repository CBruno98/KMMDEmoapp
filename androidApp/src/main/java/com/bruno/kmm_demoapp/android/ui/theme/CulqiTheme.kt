package com.culqi.commons.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@SuppressLint("ConflictingOnColor")
private val primaryColors = lightColors(
    primary = CulqiColors.colorPrimary,
    primaryVariant = CulqiColors.colorPrimaryVariant,
    secondary = CulqiColors.colorSecondary,
    error = CulqiColors.colorError,
    background = CulqiColors.colorBackground,
    surface = CulqiColors.colorSurface,
    onBackground = CulqiColors.colorOnBackground,
    onSurface = CulqiColors.colorOnSurface,
    onPrimary = CulqiColors.colorOnPrimary,
    onSecondary = CulqiColors.colorOnSecondary,
    onError = CulqiColors.colorOnError,
)

@SuppressLint("ConflictingOnColor")
private val primaryDarkColors = darkColors(
    primary = CulqiColorsDark.colorPrimary,
    primaryVariant = CulqiColorsDark.colorPrimaryVariant,
    secondary = CulqiColorsDark.colorSecondary,
    error = CulqiColorsDark.colorError,
    background = CulqiColorsDark.colorBackground,
    surface = CulqiColorsDark.colorSurface,
    onBackground = CulqiColorsDark.colorOnBackground,
    onSurface = CulqiColorsDark.colorOnSurface,
    onPrimary = CulqiColorsDark.colorOnPrimary,
    onSecondary = CulqiColorsDark.colorOnSecondary,
    onError = CulqiColorsDark.colorOnError,
)

@Composable
fun CulqiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        typography = CulqiTypography,
        content = content,
        colors = if (darkTheme) primaryDarkColors else primaryColors,
        shapes = Shapes(
            small = RoundedCornerShape(4.dp)
        )
    )
}

object CulqiTheme {

    val colors: MaterialPalette
        @Composable
        get() {
            return if (isSystemInDarkTheme()) darkColors else lightColors
        }

    val palette = ColorPalette

    private val darkColors = MaterialPalette(
        primary = palette.green.p300,
        onPrimary = palette.sky.p100,
        secondary = palette.orange.p300,
        onSecondary = palette.sky.p100,
        tertiary = palette.purple.p300,
        onTertiary = palette.sky.p100,
        primaryError = palette.negative.p200,
        onPrimaryError = palette.sky.p100,
        secondaryError = palette.negative.p300,
        onSecondaryError = palette.sky.p100,
        primaryContainer = palette.green.p500,
        onPrimaryContainer = palette.sky.p100,
        secondaryContainer = palette.orange.p500,
        onSecondaryContainer = palette.sky.p100,
        tertiaryContainer = palette.purple.p500,
        onTertiaryContainer = palette.sky.p100,
        primaryErrorContainer = palette.dark.p400,
        onPrimaryErrorContainer = palette.sky.p100,
        secondaryErrorContainer = palette.dark.p300,
        onSecondaryErrorContainer = palette.sky.p100,
        background = palette.dark.p400,
        onBackground = palette.sky.p100,
        surface = palette.dark.p300,
        onSurface = palette.sky.p100,
        onSurfaceLight = palette.sky.p200,
        outline = palette.dark.p300,
        backgroundToggleButton = palette.dark.p400,
        surfaceVariant = palette.dark.p500,
        onSurfaceVariant = palette.sky.p100,
        primaryDisabled = palette.dark.p500,
        onPrimaryDisabled = palette.dark.p300,
        info = palette.sky.p100,
        divider = palette.dark.p400,
        textPlate300 = palette.sky.p100,
        badgePassed = palette.positive.p500,
        badgeDeposit = palette.lightblue.p500,
        badgeDenied = palette.negative.p500,
        badgeCanceled = palette.warning.p500,
        badgeText = palette.sky.p100,
        badgeExpired = palette.dark.p200,

        badgeIconPassed = palette.positive.p200,
        badgeIconDeposit = palette.lightblue.p300,
        badgeIconDenied = palette.negative.p200,
        badgeIconExpired = palette.sky.p500,
        badgeIconCanceled = palette.warning.p200,
        badgeLinkIconDenied = palette.negative.p300,
        dayReportTheme = DayReportTheme(
            approvedSales = ContentDayReport(
                palette.sky.p300,
                palette.positive.p100,
                palette.positive.p500
            ),
            depositedSales = ContentDayReport(
                palette.sky.p300,
                palette.lightblue.p100,
                palette.lightblue.p500
            ),
            reversedSales = ContentDayReport(
                palette.sky.p300,
                palette.sky.p300,
                palette.plate.p200
            ),
            deniedSales = ContentDayReport(
                palette.negative.p100,
                palette.negative.p100,
                palette.negative.p500
            )
        )
    )

    private val lightColors = MaterialPalette(
        primary = palette.green.p300,
        onPrimary = palette.sky.p100,
        secondary = palette.orange.p300,
        onSecondary = palette.sky.p100,
        tertiary = palette.purple.p300,
        onTertiary = palette.sky.p100,
        primaryError = palette.negative.p300,
        onPrimaryError = palette.sky.p100,
        secondaryError = palette.negative.p300,
        onSecondaryError = palette.sky.p100,
        primaryContainer = palette.green.p100,
        onPrimaryContainer = palette.plate.p400,
        secondaryContainer = palette.orange.p100,
        onSecondaryContainer = palette.plate.p400,
        tertiaryContainer = palette.purple.p100,
        onTertiaryContainer = palette.plate.p400,
        primaryErrorContainer = palette.sky.p200,
        onPrimaryErrorContainer = palette.plate.p400,
        secondaryErrorContainer = palette.dark.p400,
        onSecondaryErrorContainer = palette.sky.p100,
        background = palette.sky.p100,
        onBackground = palette.plate.p400,
        surface = palette.sky.p200,
        onSurface = palette.plate.p400,
        onSurfaceLight = palette.sky.p300,
        outline = palette.sky.p300,
        backgroundToggleButton = palette.sky.p100,
        surfaceVariant = palette.dark.p400,
        onSurfaceVariant = palette.sky.p100,
        primaryDisabled = palette.sky.p300,
        onPrimaryDisabled = palette.sky.p500,
        info = palette.plate.p200,
        textPlate300 = palette.plate.p300,

        badgePassed = palette.positive.p100,
        badgeDeposit = palette.lightblue.p100,
        badgeDenied = palette.negative.p100,
        badgeCanceled = palette.warning.p100,
        badgeExpired = palette.sky.p300,

        badgeText = palette.plate.p400,

        badgeIconPassed = palette.positive.p300,
        badgeIconDeposit = palette.lightblue.p300,
        badgeIconDenied = palette.negative.p300,
        badgeIconCanceled = palette.warning.p300,
        badgeIconExpired = palette.sky.p500,
        badgeLinkIconDenied = palette.negative.p300,
        divider = palette.sky.p400,
        dayReportTheme = DayReportTheme(
            approvedSales = ContentDayReport(
                palette.plate.p400,
                palette.positive.p300,
                palette.positive.p100
            ),
            depositedSales = ContentDayReport(
                palette.plate.p400,
                palette.lightblue.p300,
                palette.lightblue.p100
            ),
            reversedSales = ContentDayReport(
                palette.plate.p400,
                palette.plate.p100,
                palette.sky.p300
            ),
            deniedSales = ContentDayReport(
                palette.plate.p400,
                palette.negative.p300,
                palette.negative.p100
            )
        )
    )
}