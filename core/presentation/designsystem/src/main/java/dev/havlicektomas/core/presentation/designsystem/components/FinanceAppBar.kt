package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.havlicektomas.core.presentation.designsystem.ArrowLeftIcon
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.Poppins
import dev.havlicektomas.core.presentation.designsystem.R

data class FinanceAppBarAction(
    val label: String,
    val icon: ImageVector,
    val onActionClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceAppBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    title: String,
    actions: List<FinanceAppBarAction> = emptyList(),
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                startContent?.invoke()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            if(showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = ArrowLeftIcon,
                        contentDescription = stringResource(id = R.string.go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            if (actions.isNotEmpty()) {
                actions.forEach { action ->
                    IconButton(onClick = action.onActionClick) {
                        Icon(
                            imageVector = action.icon,
                            contentDescription = action.label,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun FinanceAppBarPreview() {
    FinancemultimoduleTheme {
        FinanceAppBar(
            showBackButton = true,
            title = "sometitle",
            actions = listOf(
                FinanceAppBarAction(
                    label = "Settings",
                    icon = Icons.Outlined.Settings,
                    onActionClick = {}
                )
            ),
            onBackClick = {},
            startContent = null
        )
    }
}