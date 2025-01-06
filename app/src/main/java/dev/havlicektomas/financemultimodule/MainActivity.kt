package dev.havlicektomas.financemultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dev.havlicektomas.auth.presentation.intro.IntroScreenRoot
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancemultimoduleTheme {
                val navController = rememberNavController()
                NavigationRoot(navController = navController)
            }
        }
    }
}