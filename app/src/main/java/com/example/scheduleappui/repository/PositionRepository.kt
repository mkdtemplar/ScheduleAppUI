package com.example.scheduleappui.repository

import com.example.scheduleappui.models.Position
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.scheduleappui.Result

class PositionRepository(private val firestore : FirebaseFirestore) {

    suspend fun getPositions(): Result<List<Position>> = try {
        val querySnapshot = firestore.collection("positions").get().await()
        val positions = querySnapshot.documents.map { document ->
            document.toObject(Position::class.java)!!.copy(id = document.id)
        }
        Result.Success(positions)
    } catch (e: Exception) {
        Result.Error(e)
    }
}