package com.culqi.commons.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bruno.kmm_demoapp.android.R

val CulqiFontFamily = FontFamily(
    Font(R.font.lexenddeca_w100, FontWeight.W100),
    Font(R.font.lexenddeca_w200, FontWeight.W200),
    Font(R.font.lexenddeca_w300, FontWeight.W300),
    Font(R.font.lexenddeca_w400, FontWeight.W400),
    Font(R.font.lexenddeca_w500, FontWeight.W500),
    Font(R.font.lexenddeca_w600, FontWeight.W600),
    Font(R.font.lexenddeca_w700, FontWeight.W700),
    Font(R.font.lexenddeca_w800, FontWeight.W800),
    Font(R.font.lexenddeca_w900, FontWeight.W900)
)

val CulqiTypography = Typography(
    defaultFontFamily = CulqiFontFamily
)