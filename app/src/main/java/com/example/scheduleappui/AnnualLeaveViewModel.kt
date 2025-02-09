package com.example.scheduleappui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleappui.models.AnnualLeaveModel
import com.example.scheduleappui.repository.AnnualLeaveRepository
import kotlinx.coroutines.launch

class AnnualLeaveViewModel : ViewModel() {

    private val _selectedDateMillis = MutableLiveData<AnnualLeaveModel?>()
    val selectedDateMillis: LiveData<AnnualLeaveModel?> get() = _selectedDateMillis

    private val annualLeaveRepository : AnnualLeaveRepository
    init {
        annualLeaveRepository = AnnualLeaveRepository(Injection.instance())
    }

    fun setSelectedDate(dateMillis: AnnualLeaveModel?) {
        _selectedDateMillis.value = dateMillis
    }

    fun createAnnualLeave(startDate : String, endDate : String, userEmail : String) {
        viewModelScope.launch {
            val result = annualLeaveRepository.createAnnualLeave(startDate, endDate, userEmail)
        }
    }
}