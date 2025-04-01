package dev.havlicektomas.finance.presentation.transaction_new

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import dev.havlicektomas.core.domain.finance.getTransactionCategories
import dev.havlicektomas.core.domain.finance.getTransactionCategory
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceActionButton
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceBasicTextField
import dev.havlicektomas.core.presentation.designsystem.components.FinanceDropDown
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import dev.havlicektomas.core.presentation.designsystem.components.FinanceUnitTextField
import dev.havlicektomas.core.presentation.ui.ObserveAsEvents
import dev.havlicektomas.finance.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionNewScreenRoot(
    viewModel: TransactionNewViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is TransactionNewEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            TransactionNewEvent.TransactionSaved -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.transaction_created,
                    Toast.LENGTH_LONG
                ).show()
                onNavigateBack()
            }
        }
    }

    TransactionNewScreen(
        state = viewModel.state,
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
    state: TransactionNewState,
    onAction: (TransactionNewAction) -> Unit
) {
    FinanceScaffold(
        topAppBar = {
            FinanceAppBar(
                showBackButton = true,
                title = stringResource(R.string.create_transaction),
                actions = listOf(
                    FinanceAppBarAction(
                        label = stringResource(R.string.close),
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
                text = stringResource(R.string.create),
                isLoading = state.isSavingTransaction,
                enabled = state.canCreateTransaction
            ) {
                onAction(TransactionNewAction.OnCreateTransactionClick)
            }
        }
    ) { innerPadding ->
        var categoryMenuExpanded by remember { mutableStateOf(false) }

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
                for (type in FinanceTransactionType.entries) {
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = type.ordinal,
                            count = FinanceTransactionType.entries.size
                        ),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        onClick = {
                            onAction(TransactionNewAction.OnTransactionTypeSelected(type))
                        },
                        selected = type == state.transaction.type) {
                        Text(
                            text = type.name.lowercase()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FinanceBasicTextField(
                    value = state.transaction.title,
                    onValueChange = {
                        onAction(TransactionNewAction.OnTransactionTitleChanged(it))
                    },
                    hint = stringResource(R.string.transaction_title_hint),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(16.dp))
                FinanceUnitTextField(
                    value = state.transaction.amount.toString(),
                    onValueChange = {
                        onAction(TransactionNewAction.OnTransactionAmountChanged(it.toDouble()))
                    },
                    unit = if (state.transaction.type == FinanceTransactionType.EXPENSE) "-$" else "$",
                    keyboardType = KeyboardType.Decimal,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    unitColor = if (state.transaction.type == FinanceTransactionType.EXPENSE) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                FinanceBasicTextField(
                    value = state.transaction.note,
                    onValueChange = {
                        onAction(TransactionNewAction.OnTransactionNoteChanged(it))
                    },
                    hint = stringResource(R.string.transaction_note_hint),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(72.dp))
                FinanceDropDown(
                    expanded = categoryMenuExpanded,
                    onDropdownMenuItemClicked = { item ->
                        onAction(TransactionNewAction.OnTransactionCategorySelected(getTransactionCategory(item)))
                        categoryMenuExpanded = false
                    },
                    onDropdownMenuClicked = {
                        categoryMenuExpanded = !categoryMenuExpanded
                    },
                    items = getTransactionCategories(),
                    selectedItem = state.transaction.category.name.lowercase()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(kotlin. uuid. ExperimentalUuidApi::class)
@PreviewLightDark
@Composable
private fun TransactionNewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionNewScreen(
            state = TransactionNewState(
                transaction = FinanceTransaction(
                    type = FinanceTransactionType.EXPENSE,
                    amount = 158.25,
                    title = "Test",
                    note = "Test note",
                    category = FinanceTransactionCategory.OTHER
                )
            ),
            onAction = {}
        )
    }
}