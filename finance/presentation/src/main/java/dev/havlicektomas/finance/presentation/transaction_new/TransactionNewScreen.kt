package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceActionButton
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import dev.havlicektomas.core.presentation.designsystem.components.FinanceUnitTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionNewScreenRoot(
    viewModel: TransactionNewViewModel = koinViewModel()
) {
    TransactionNewScreen(
        state = viewModel.state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionNewScreen(
    state: TransactionNewState
) {
    FinanceScaffold(
        topAppBar = {
            FinanceAppBar(
                showBackButton = true,
                title = "Create transaction",
                actions = listOf(
                    FinanceAppBarAction(
                        label = "Close",
                        icon = Icons.Default.Close,
                        onActionClick = {}
                    )
                ),
                onBackClick = {},
            )
        }
    ) { innerPadding ->

        var testAmount by remember { mutableStateOf("00.00") }
        var selectedIndex by remember { mutableStateOf(0) }
        val options = listOf("Expense", "Income")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                options. forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex) {
                            Text(label)
                        }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "+ Add Title",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                FinanceUnitTextField(
                    value = testAmount,
                    onValueChange = { testAmount = it },
                    unit = "$",
                    keyboardType = KeyboardType.Decimal,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    unitColor = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "+ Add Note",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FinanceActionButton(
                modifier = Modifier
                    .padding(16.dp),
                text = "Create",
                isLoading = false,
                enabled = true
            ) { }
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionNewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionNewScreen(
            state = TransactionNewState()
        )
    }
}