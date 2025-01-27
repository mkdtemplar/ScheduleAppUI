package com.example.scheduleappui.repository


import com.example.scheduleappui.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.scheduleappui.Result

class UserRepository(private val auth : FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}
