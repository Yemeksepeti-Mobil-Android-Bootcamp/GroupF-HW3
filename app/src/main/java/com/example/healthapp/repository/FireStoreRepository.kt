package com.example.healthapp.repository

import com.example.healthapp.data.entity.user.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreRepository {

    var firestoreDB = FirebaseFirestore.getInstance()

    fun sendUserInformationToFirestore(user: User) {
        firestoreDB.collection("Users").document(user.id).set(user, SetOptions.merge())
            .addOnSuccessListener {
                println("successful")
            }
            .addOnFailureListener {
                println("not successful")
                println(it)
            }
    }
}