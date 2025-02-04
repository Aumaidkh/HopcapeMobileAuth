package com.hopcape.m.auth.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hopcape.mobile.auth.presentation.screens.utils.progressBar
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily

@Composable
fun ProgressButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    progressColor: Color = MaterialTheme.colors.primary,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)),
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    border: BorderStroke? = null,
    contentAlignment: Alignment = Alignment.Center,
    progressSize: Dp = 24.dp,
    progressStrokeWidth: Dp = 2.dp,
    fontFamily: FontFamily = LocalUrbanistFontFamily.current
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(55.dp),
        enabled = enabled && !isLoading,
        colors = buttonColors,
        contentPadding = contentPadding,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        Box(
            contentAlignment = contentAlignment,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = progressColor,
                    modifier = Modifier
                        .testTag(progressBar)
                        .size(progressSize),
                    strokeWidth = progressStrokeWidth
                )
            } else {
                Text(
                    text = text,
                    style = MaterialTheme.typography.button,
                    fontFamily = fontFamily
                )
            }
        }
    }
}