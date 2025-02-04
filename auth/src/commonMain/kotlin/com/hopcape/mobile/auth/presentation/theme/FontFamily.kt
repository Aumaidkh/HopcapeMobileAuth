package com.hopcape.mobile.auth.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import hopcapemobileauth.auth.generated.resources.Res
import hopcapemobileauth.auth.generated.resources.urbanist
import hopcapemobileauth.auth.generated.resources.urbanist_medium
import hopcapemobileauth.auth.generated.resources.urbanist_semibold
import org.jetbrains.compose.resources.Font


@Composable
fun urbanistFontFamily() =
    FontFamily(
        Font(
            resource = Res.font.urbanist,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.urbanist_medium,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.urbanist_semibold,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
)