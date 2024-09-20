package org.example.aok.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ),
    borderColor : Color? = null,
    content: @Composable () -> Unit = {}
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        onClick = { onClick() },
        border = borderColor?.let { BorderStroke(2.dp, it) }
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            content()
        }
    }
}