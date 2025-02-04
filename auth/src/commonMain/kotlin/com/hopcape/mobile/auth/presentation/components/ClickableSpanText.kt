package com.hopcape.m.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily

@Composable
fun ClickableSpanText(
    modifier: Modifier = Modifier,
    text: String,
    clickableText: String,
    onClick: () -> Unit,
    verticalPadding: Dp = 40.dp,
    fontFamily: FontFamily = LocalUrbanistFontFamily.current
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                fontSize = 14.sp
            )
        ) {
            append(text)
        }
        append(" ")
        pushStringAnnotation(
            tag = clickableText,
            annotation = clickableText
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        ) {
            append(clickableText)
        }
        pop()
    }

    ClickableText(
        modifier = modifier
            .padding(vertical = verticalPadding)
            .fillMaxWidth(),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = clickableText,
                start = offset,
                end = offset
            )
                .firstOrNull()?.let {
                    onClick()
                }
        },
        style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center, fontFamily = fontFamily, fontSize = 12.sp)
    )
}