package com.alihafez.search.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alihafez.core.R
import com.alihafez.core.presentation.ScreenContainer
import com.alihafez.core.presentation.common.WeatherUi
import com.alihafez.core.presentation.preview.PreviewLightDarkMode
import com.alihafez.search.presentation.component.DialogResult
import com.alihafez.search.presentation.component.SavedItem

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@Composable
fun SearchRoute(modifier: Modifier = Modifier, viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScreenContainer(
        modifier = modifier.fillMaxSize(),
        isLoading = uiState.isLoading,
        errorDialogText = uiState.hasError,
        screen = {
            SearchScreen(
                modifier = modifier,
                state = uiState,
                onCityNameChange = viewModel::onCityNameChange,
                onSearchClicked = viewModel::onSearchIconClick,
                onSavedLocationClick = viewModel::onSavedLocationClick,
                onDismiss = viewModel::onDismiss
            )
        })
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onCityNameChange: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onSavedLocationClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {

    Scaffold(
        modifier = modifier.padding(16.dp),
        topBar = {
            TextField(
                value = state.cityName,
                onValueChange = onCityNameChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                maxLines = 1,
                placeholder = {
                    Text(stringResource(R.string.placeholder_search))
                },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondaryContainer),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    errorContainerColor = MaterialTheme.colorScheme.errorContainer
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked()
                    }
                ),
                shape = RoundedCornerShape(6.dp)
            )
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                if (state.weatherUi != null) {
                    DialogResult(
                        state = state,
                        onDismiss = onDismiss,
                        onSavedLocationClick = onSavedLocationClick
                    )
                }

            }
            items(state.savedLocations) { item ->
                SavedItem(savedLocations = item)
            }
        }
    }
}

@PreviewLightDarkMode
@Composable
private fun SearchScreenPreview() {
    SearchScreen(state = SearchUiState(weatherUi = WeatherUi()))
}