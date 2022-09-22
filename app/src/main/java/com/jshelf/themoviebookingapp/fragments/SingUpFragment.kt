package com.jshelf.themoviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jshelf.themoviebookingapp.delegates.LoginDelegate
import com.jshelf.themoviebookingapp.delegates.PassDataDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewpods.fbgooglebtnViewpod
import kotlinx.android.synthetic.main.fragment_signup.*

class SingUpFragment() : Fragment(), LoginDelegate {
    lateinit var btnSignUpViewpod: fbgooglebtnViewpod
    lateinit var msignUpDelegate: PassDataDelegate//variable nullable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        msignUpDelegate = context as PassDataDelegate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUpViewpod = vpLoginSignUp2 as fbgooglebtnViewpod
        btnSignUpViewpod.setUpLoginViewpod(this)



//        view.btnConfirm.setOnClickListener {
//            val intent= Intent(activity,HomeActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onTapLoginSignUpConfirm() {
        msignUpDelegate.onPassSignUpData(
            txtEmail.text.toString(), txtPassword.text.toString(),
            txtName.text.toString(), txtPhone.text.toString()
        )
    }

}