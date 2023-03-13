package com.csci448.sflemington.recallrumble.presentation.components

import android.util.Log
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AnswerButton(buttonText: String, onButtonClick: () -> Unit, colors: ButtonColors, enabled: Boolean = true) {
    ElevatedButton(onClick = onButtonClick, enabled = enabled, colors = colors, elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp, pressedElevation = 8.dp, focusedElevation =2.dp, hoveredElevation = 2.dp, disabledElevation = 0.dp)) {
        Text(text = buttonText)
    }
}