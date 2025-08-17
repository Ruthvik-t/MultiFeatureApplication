package com.ruthvik.multifeature.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier
){
    CircularProgressIndicator(modifier = modifier)
}