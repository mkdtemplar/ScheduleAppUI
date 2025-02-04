package com.example.scheduleappui.repository

import com.example.scheduleappui.models.AssignmentsModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.scheduleappui.Result

class AssignmentsRepository(private val firestore : FirebaseFirestore) {


    suspend fun getAllAssigments(): Result<List<AssignmentsModel>> = try {
        val querySnapshot = firestore.collection("assignments").get().await()
        val assignments = querySnapshot.documents.map { document ->
            document.toObject(AssignmentsModel::class.java)!!.copy(id = document.id)
        }
        Result.Success(assignments)
    } catch (e: Exception) {
        Result.Error(e)
    }

    }