package com.example.healthapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthapp.R
import com.example.healthapp.data.model.User
import com.example.healthapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class RegisterFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private val mFireStore = FirebaseFirestore.getInstance()

    var bloodGroups = arrayOf<String?>("A+", "A-", "B-", "B+", "AB-", "AB+" ,"0-", "0+")

    lateinit var kanGrubu : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnRegister.setOnClickListener {
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                val name = editTextName.text.toString()
                val weight = editTextWeight.text.toString()
                val height = editTextHeight.text.toString()
                val bloodGroup = kanGrubu
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        println("successful")
                        val firebaseUser: FirebaseUser = it.result!!.user!!
                        val user = User(
                            firebaseUser.uid,
                            name,
                            email,
                            height,
                            weight,
                            bloodGroup
                        )
                        sendUserInformationToFirestore(user)

                    }
                }.addOnFailureListener {
                    println("not successful")
                }

            }
        }
        binding.spinner.onItemSelectedListener = this


        val ad: ArrayAdapter<*> =
            ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, bloodGroups)

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        binding.spinner.adapter = ad

    }

    fun sendUserInformationToFirestore(user: User) {
        mFireStore.collection("Users")
            .document(user.id)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(context, "BAŞARILI", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Başarısız", Toast.LENGTH_SHORT).show()
            }

    }

// Create an ArrayAdapter using the string array and a default spinner layout

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(requireContext(), bloodGroups[position], Toast.LENGTH_LONG).show()
        kanGrubu = bloodGroups[position].toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}