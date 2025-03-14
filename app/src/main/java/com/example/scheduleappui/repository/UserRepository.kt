package com.example.scheduleappui.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.scheduleappui.Result

class UserRepository(private val auth: FirebaseAuth, instance: FirebaseFirestore) {

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    fun logout()  : Result<Boolean> = try {
        auth.signOut()
        Result.Success(true)
    } catch (e : Exception) {
        Result.Error(e)
    }
}
