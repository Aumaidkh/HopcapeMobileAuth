package com.hopcape.mobile.auth.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily

@Composable
fun OutlinedInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    contentColor: Color = MaterialTheme.colors.onSurface,
    fontFamily: FontFamily = LocalUrbanistFontFamily.current
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle.copy(color = contentColor, fontFamily = fontFamily),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
            backgroundColor = Color.Transparent,
            errorLeadingIconColor = MaterialTheme.colors.error,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = textStyle.copy(color = contentColor.copy(alpha = 0.4f), fontSize = 14.sp)
            )
        },
        leadingIcon = {
            if (startIcon != null) {
                startIcon()
            }
        },
        shape = RoundedCornerShape(
            8.dp
        ),
        isError = isError,
        trailingIcon = {
            if (endIcon != null) {
                endIcon()
            }
        },
        singleLine = true,
        maxLines = 1,
    )
}