package com.ruthvik.multifeature.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

enum class TypographyType {
    Heading,
    Title,
    Label,
    Subtext
}

@Composable
fun AppTextView(
    text: String,
    typographyType: TypographyType,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: TextUnit? = null,
    overflow: TextOverflow = TextOverflow.Clip
) {
    val textStyle: TextStyle = when (typographyType) {
        TypographyType.Heading -> MaterialTheme.typography.headlineLarge
        TypographyType.Title -> MaterialTheme.typography.titleLarge
        TypographyType.Label -> MaterialTheme.typography.labelLarge
        TypographyType.Subtext -> MaterialTheme.typography.bodySmall
    }

    Text(
        text = text,
        style = textStyle.copy(fontSize = fontSize ?: textStyle.fontSize),
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}