package com.example.healthapp.ui.register


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthapp.data.FireStoreRepository
import com.example.healthapp.data.entity.user.User


class RegisterViewModel  : ViewModel() {

    var firebaseRepository = FireStoreRepository()

    // save user to firebase
    fun saveUserToFirebase(user: User){
        firebaseRepository.sendUserInformationToFirestore(user)
    }


}
