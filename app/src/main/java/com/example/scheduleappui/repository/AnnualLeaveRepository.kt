package com.example.scheduleappui.repository

import com.example.scheduleappui.Result
import com.example.scheduleappui.models.AnnualLeaveModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AnnualLeaveRepository(private val firestore : FirebaseFirestore) {

    suspend fun createAnnualLeave(
        starDate : String,
        endDate : String,
        userEmail : String) : Result<Unit> =
      try {
          val annualLeave = AnnualLeaveModel(starDate, endDate, userEmail)
          firestore.collection("annualleave").document().set(annualLeave).await()
          Result.Success(Unit)
      } catch (e : Exception) {
          Result.Error(e)
      }
}