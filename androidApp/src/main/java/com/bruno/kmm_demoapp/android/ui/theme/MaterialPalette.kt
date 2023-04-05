package com.culqi.commons.ui.theme

import androidx.compose.ui.graphics.Color

data class MaterialPalette(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val primaryError: Color,
    val onPrimaryError: Color,
    val secondaryError: Color,
    val onSecondaryError: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val primaryErrorContainer: Color,
    val onPrimaryErrorContainer: Color,
    val secondaryErrorContainer: Color,
    val onSecondaryErrorContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val onSurfaceLight: Color,
    val outline: Color,
    val backgroundToggleButton: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val primaryDisabled: Color,
    val onPrimaryDisabled: Color,
    val info: Color,
    val divider: Color,
    val textPlate300: Color,
    val badgePassed: Color,
    val badgeDeposit: Color,
    val badgeDenied: Color,
    val badgeExpired: Color,
    val badgeCanceled: Color,
    val badgeText: Color,

    val badgeIconPassed: Color,
    val badgeIconDeposit: Color,
    val badgeIconDenied: Color,
    val badgeIconExpired: Color,
    val badgeIconCanceled: Color,
    val badgeLinkIconDenied: Color,
    val dayReportTheme: DayReportTheme
)

data class DayReportTheme(
    val approvedSales: ContentDayReport,
    val depositedSales: ContentDayReport,
    val reversedSales: ContentDayReport,
    val deniedSales: ContentDayReport
)

data class ContentDayReport(
    val title: Color,
    val subTitle: Color,
    val background: Color
)