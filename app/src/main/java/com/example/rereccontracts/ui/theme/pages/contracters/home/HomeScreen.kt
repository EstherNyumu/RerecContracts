package com.example.rereccontracts.ui.theme.pages.contracters.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface {
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}