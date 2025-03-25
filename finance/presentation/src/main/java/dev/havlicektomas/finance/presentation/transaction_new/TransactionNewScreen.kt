package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableIntStateOf
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
import dev.havlicektomas.core.presentation.designsystem.components.FinanceBasicTextField
import dev.havlicektomas.core.presentation.designsystem.components.FinanceDropDown
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import dev.havlicektomas.core.presentation.designsystem.components.FinanceUnitTextField
import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionNewScreenRoot(
    viewModel: TransactionNewViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    TransactionNewScreen(
        onAction = { action ->
            if (action is TransactionNewAction.OnBackClick) {
                onNavigateBack()
            } else {
                viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionNewScreen(
    onAction: (TransactionNewAction) -> Unit
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
                        onActionClick = {
                            onAction(TransactionNewAction.OnBackClick)
                        }
                    )
                ),
                onBackClick = {
                    onAction(TransactionNewAction.OnBackClick)
                },
            )
        },
        bottomBar = {
            FinanceActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 16.dp),
                text = "Create",
                isLoading = false,
                enabled = true
            ) { }
        }
    ) { innerPadding ->

        var categoryMenuExpanded by remember { mutableStateOf(false) }

        // TODO: Replace by TransactionNewState - transaction title
        var testTitle by remember { mutableStateOf("") }

        // TODO: Replace by TransactionNewState - transaction note
        var testNote by remember { mutableStateOf("") }

        // TODO: Replace by TransactionNewState - transaction amount
        var testAmount by remember { mutableStateOf("00.00") }

        // TODO: Replace by TransactionNewState - transaction type
        var selectedType by remember { mutableIntStateOf(0) }
        val types = listOf("Expense", "Income")

        // TODO: Replace by TransactionNewState - transaction category
        var selectedCategory by remember { mutableStateOf("Other") }
        val categories = listOf("Clothing", "Education", "Entertainment", "Food", "Health", "Other")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                types. forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = types.size
                        ),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        onClick = { selectedType = index },
                        selected = index == selectedType) {
                            Text(label)
                        }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FinanceBasicTextField(
                    value = testTitle,
                    onValueChange = { testTitle = it },
                    hint = "+ Add Title",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(16.dp))
                FinanceUnitTextField(
                    value = testAmount,
                    onValueChange = { testAmount = it },
                    unit = if (selectedType == 0) "-$" else "$",
                    keyboardType = KeyboardType.Decimal,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    unitColor = if (selectedType == 0) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                FinanceBasicTextField(
                    value = testNote,
                    onValueChange = { testNote = it },
                    hint = "+ Add Note",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(72.dp))
                FinanceDropDown(
                    expanded = categoryMenuExpanded,
                    onDropdownMenuItemClicked = { item ->
                        selectedCategory = item
                        categoryMenuExpanded = false
                    },
                    onDropdownMenuClicked = {
                        categoryMenuExpanded = !categoryMenuExpanded
                    },
                    items = categories,
                    selectedItem = selectedCategory
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionNewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionNewScreen(
            onAction = {}
        )
    }
}