package com.ruthvik.multifeature.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ruthvik.multifeature.common.AppTextView
import com.ruthvik.multifeature.common.TypographyType

@Composable
fun HomeScreen(
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        AppTextView(
            text = "Welcome, $name!",
            typographyType = TypographyType.Title
        )
    }
}