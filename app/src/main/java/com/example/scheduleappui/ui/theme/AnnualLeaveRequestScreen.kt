package com.example.scheduleappui.ui.theme

import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
//noinspection UsingMaterialAndMaterial3Libraries

import androidx.compose.material3.DatePickerDialog
import android.os.Build

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.time.Instant

import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnualLeaveRequestScreen() {

    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }
    var showStartDateDialog by remember { mutableStateOf(false) }
    var showEndDateDialog by remember { mutableStateOf(false) }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {showStartDateDialog = true}) {
                    Text(text = "Pick starting date")
                }

                Button(onClick = {showEndDateDialog = true}) {
                    Text("Select end date")
                }
            }

            if (showStartDateDialog) {
                AnnualLeaveDialog {
                    showStartDateDialog = false
                }
            }

            if (showEndDateDialog) {
                AnnualLeaveDialog { showEndDateDialog = false }
            }

        }

}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDate(millis: Long): ZonedDateTime {
    // Interpret the milliseconds as the start of the day in UTC, then convert to Los Angeles time
    val utcDateAtStartOfDay = Instant
        .ofEpochMilli(millis)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()
    println("UTC Date at Start of Day: $utcDateAtStartOfDay") // Debugging UTC date

    // Convert to the same instant in Local time zone
    val localDate = utcDateAtStartOfDay.atStartOfDay(ZoneId.systemDefault())
    println("Local Date: $localDate") // Debugging local date

    return localDate

}
@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(date: ZonedDateTime): String {
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.getDefault())
    return dateFormatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnualLeaveDialog(onDismiss : () -> Unit) {
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
       convertMillisToLocalDate(it)
    }

    val dateToString = millisToLocalDate?.let {
        dateToString(millisToLocalDate)
    }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss){
                Text("Select")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = dateState, showModeToggle = true)
    }

}