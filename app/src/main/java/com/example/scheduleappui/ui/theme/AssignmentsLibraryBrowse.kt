package com.example.scheduleappui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleappui.AssignmentsViewModel
import com.example.scheduleappui.R

@Composable
fun AssignmentsLibraryBrowse() {
    val assignmentsViewModel : AssignmentsViewModel = viewModel()
    val assignments by assignmentsViewModel.assignments.observeAsState(emptyList())
    val categories = listOf("V.I.P Entrance Assignments", "Main Entrance Assignments",
        "Armory Assignments", "Potus Assignments")
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(assignments) {
            cat ->
            AssingnmentItem(cat.assignments, drawable = R.drawable.baseline_apps_24)
        }
    }
}