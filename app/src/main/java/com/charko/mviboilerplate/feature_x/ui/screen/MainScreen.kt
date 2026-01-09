package com.charko.mviboilerplate.feature_x.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.charko.mviboilerplate.feature_x.presentation.MainViewModel
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiEvent
import com.charko.mviboilerplate.feature_x.presentation.contact.MainUiState
import com.charko.mviboilerplate.ui.theme.Typography

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewmodel: MainViewModel = hiltViewModel()
) {
    val state by viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewmodel.setEvent(MainUiEvent.FetchCountInfo)
    }

    MainScreenContent(
        modifier = modifier,
        state = state,
        event = { event ->
            viewmodel.setEvent(event)
        }
    )
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    state: MainUiState = MainUiState(false, 0, null),
    event: (MainUiEvent) -> Unit,
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Counter Test App",
            style = Typography.headlineMedium.copy(color = Color(0xFF1a1a1a)),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "${state.count}",
            style = Typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .padding(5.dp),
                onClick = {
                    event.invoke(MainUiEvent.OnIncrementClicked)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "증가")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "증가")
            }

            Button(
                modifier = Modifier
                    .height(70.dp)
                    .padding(5.dp),
                onClick = {
                    event.invoke(MainUiEvent.OnDecrementClicked)
                },
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "감소")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "감소")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent(
        state = MainUiState(false, 0, null),
        event = {}
    )
}