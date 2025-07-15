package com.example.incomeexpensesapplication.UI_

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



    @Composable
    @Preview
    fun Window() {
        Column(
            Modifier.Companion.fillMaxSize(),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var text by remember { mutableStateOf("") }
            TextField(

                value = text,
                onValueChange = { text = it },
                label = { Text("Введите Текст") },
                modifier = Modifier.Companion.fillMaxWidth()

            )

            FloatingActionButton(
                modifier = Modifier.Companion.width(200.dp)
                    .padding(35.dp),
                onClick = {

                },

                ) {
                Text(text = "Submit")
            }

        }
    }



