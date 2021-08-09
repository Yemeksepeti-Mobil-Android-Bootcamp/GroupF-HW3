package com.example.healthapp.view_pager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthapp.R
import com.example.healthapp.data.entity.user.User
import com.example.healthapp.databinding.FragmentRegisterBinding
import com.example.healthapp.databinding.FragmentUserBinding
import com.example.healthapp.databinding.FragmentUserInfoBinding
import com.example.healthapp.ui.hospital.hospital_detail.HospitalDetailFragmentArgs
import com.example.healthapp.ui.register.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private val mFireStore= FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                binding.weightValue.text = user?.weight
                binding.heightValue.text = user?.height
                binding.bloodGroupValue.text = user?.bloodGroup
                binding.emailValue.text = user?.email
            }
    }

    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}