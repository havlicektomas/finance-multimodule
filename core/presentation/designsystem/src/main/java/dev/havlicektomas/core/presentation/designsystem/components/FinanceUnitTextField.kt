package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

@Composable
fun FinanceUnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier,
    color: Color,
    unitColor: Color,
    fontSize: TextUnit,
    keyboardType: KeyboardType
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline),
            color = unitColor,
            fontSize = fontSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = color,
                fontSize = fontSize
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignBy(LastBaseline)
        )
    }
}

@PreviewLightDark
@Composable
private fun FinanceUnitTextFieldPreview() {
    FinancemultimoduleTheme {
        FinanceUnitTextField(
            value = "10.25",
            onValueChange = {},
            unit = "-$",
            keyboardType = KeyboardType.Decimal,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 36.sp,
            unitColor = MaterialTheme.colorScheme.error
        )
    }
}