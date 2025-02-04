package com.example.scheduleappui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleappui.models.AssignmentsModel
import com.example.scheduleappui.repository.AssignmentsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class AssignmentsViewModel : ViewModel() {
    private val _assignments = MutableLiveData<List<AssignmentsModel>>()
    val assignments : LiveData<List<AssignmentsModel>> get() = _assignments

    private val assignmentsRepository : AssignmentsRepository


    init {
        assignmentsRepository = AssignmentsRepository(InjectionAssigments.instanceAssignments())
        loadAssignments()
    }


    fun loadAssignments() {
        viewModelScope.launch {
            when (val result = assignmentsRepository.getAllAssigments()) {
                is Result.Success -> {
                    _assignments.value = result.data
                    Log.d("AssignmentsViewModel", "Assignments loaded: ${result.data.size} items")
                }
                is Result.Error -> {
                    Log.e("AssignmentsViewModel", "Error loading assignments", result.exception)
                }
            }
        }
    }

}

object InjectionAssigments {
    private val instanceAssignments: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun instanceAssignments(): FirebaseFirestore {
        return instanceAssignments
    }
}