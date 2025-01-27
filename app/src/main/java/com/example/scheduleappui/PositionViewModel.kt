package com.example.scheduleappui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleappui.data.Position
import com.example.scheduleappui.repository.PositionRepository
import kotlinx.coroutines.launch


class PositionViewModel : ViewModel() {

    private val _positions = MutableLiveData<List<Position>>()
    val positions : LiveData<List<Position>> get() = _positions

    private val positionRepository : PositionRepository

    init {
        positionRepository = PositionRepository(Injection.instance())
        loadPositions()
    }

    fun loadPositions() {
        viewModelScope.launch {
            when (val result = positionRepository.getPositions()) {
                is Result.Error -> {}
                is Result.Success -> _positions.value = result.data
            }
        }
    }
}