package com.example.scheduleappui.ui.theme

import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import android.app.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.R
import java.util.Date
import android.widget.DatePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnualLeaveRequestScreen() {

    val startDate = remember { mutableStateOf(Calendar.getInstance()) }
    val endDate = remember { mutableStateOf(Calendar.getInstance()) }
    val showStartDateDialog = remember { mutableStateOf(false) }
    val showEndDateDialog = remember { mutableStateOf(false) }

    Scaffold( topBar = {
        TopAppBar(title = {
            Text("Annual leave request", textAlign = TextAlign.Center)
        },
            colors = topAppBarColors(
                containerColor = colorResource(id = R.color.app_bar_color),
                titleContentColor = colorResource(id = R.color.white),
            ),
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {showStartDateDialog.value = true}) {
                    Text(text = "Pick starting date")
                }

                Button(modifier = Modifier.fillMaxSize(), onClick = {showEndDateDialog.value = true}) {
                    Text(text = "Pick end date")
                }
            }

            if (showStartDateDialog.value) {

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerContent(context: Context) {
    val year : Int
    val month : Int
    val day : Int
    val selectedDate = remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        {_ : DatePicker, year :Int, month: kotlin.Int, day : kotlin.Int ->
            selectedDate.value = "$day/${month + 1}/$year"
        }, year, month, day

    )
}