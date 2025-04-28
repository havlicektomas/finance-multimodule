package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

@Composable
fun FinanceBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color,
    fontSize: TextUnit,
    hint: String,
    keyboardType: KeyboardType
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
            },
        textStyle = LocalTextStyle.current.copy(
            color = color,
            fontSize = fontSize,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            if(value.isEmpty() && !isFocused) {
                Text(
                    text = hint
                )
            }
            innerTextField()
        }
    )
}

@PreviewLightDark
@Composable
private fun FinanceBasicTextFieldPreview() {
    FinancemultimoduleTheme {
        FinanceBasicTextField(
            value = "",
            onValueChange = {},
            hint = "+ Add Title",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            keyboardType = KeyboardType.Text
        )
    }
}