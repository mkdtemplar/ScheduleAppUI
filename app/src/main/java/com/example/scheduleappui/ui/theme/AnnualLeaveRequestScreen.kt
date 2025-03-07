package com.example.scheduleappui.ui.theme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import android.os.Build
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleappui.AnnualLeaveViewModel
import com.example.scheduleappui.userpreferences.UserPreferences
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.scheduleappui.sendmail.SendEmail
import kotlinx.coroutines.DelicateCoroutinesApi

var startDateGlobal : String = ""
var endDateGlobal : String = ""

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun AnnualLeaveRequestScreen() {

    var showStartDateDialog by remember { mutableStateOf(false) }
    var showEndDateDialog by remember { mutableStateOf(false) }
    val annualLeaveViewModel: AnnualLeaveViewModel = viewModel()
    val context:Context = LocalContext.current

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
            Column(modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = {
                           val result =  annualLeaveViewModel.createAnnualLeave(startDateGlobal,
                                endDateGlobal, UserPreferences.getEmail(context).toString())
                            if (result.toString().isNotEmpty()) {
                                SendEmail(context, UserPreferences.getEmail(context).toString(),
                                    "Annual leave from $startDateGlobal until $endDateGlobal"
                                    )
                            } else {
                                Toast.makeText(context, "Annual leave NOT submitted ", Toast.LENGTH_LONG).show()
                            }
                        },

                    ) {
                        Text("Submit")
                    }
                }
            }

            if (showStartDateDialog) {
                startDateGlobal = annualLeaveDialog {
                    showStartDateDialog = false
                }
            }

            if (showEndDateDialog) {
               endDateGlobal =  annualLeaveDialog { showEndDateDialog = false }
            }
        }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDate(millis: Long): ZonedDateTime {
    val utcDateAtStartOfDay = Instant
        .ofEpochMilli(millis)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()

    val localDate = utcDateAtStartOfDay.atStartOfDay(ZoneId.systemDefault())

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
fun annualLeaveDialog(onDismiss : () -> Unit) : String{
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
       convertMillisToLocalDate(it)
    }

    var dateString = millisToLocalDate?.let {
        dateToString(millisToLocalDate)
    }.toString()

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

    return dateString
}