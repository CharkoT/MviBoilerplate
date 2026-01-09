package com.charko.mviboilerplate.feature_x.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import com.charko.mviboilerplate.feature_x.presentation.MainViewModel
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiEffect
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiState
import com.charko.mviboilerplate.ui.theme.MviBoilerplateTheme
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.charko.mviboilerplate.feature_x.ui.screen.MainScreen
import com.charko.mviboilerplate.feature_x.ui.screen.MainScreenContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MviBoilerplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                    )
                }
            }
        }

        initCollectors()
    }

    private fun initCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        render(state)
                    }
                }

                launch {
                    viewModel.uiEffect.collect { effect ->
                        handleEffect(effect)
                    }
                }
            }
        }
    }

    private fun render(state: MainUiState) {
        if (state.isLoading) {

        }
    }

    private fun handleEffect(effect: MainUiEffect) {
        when (effect) {
            is MainUiEffect.ShowToast -> {
                Toast.makeText(this, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MviBoilerplateTheme {
        MainScreenContent(
            state = MainUiState(false, 0, null),
            event = {}
        )
    }
}