package com.jshelf.themoviebookingapp.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.delegates.LoginDelegate
import com.jshelf.themoviebookingapp.delegates.PassDataDelegate
import com.jshelf.themoviebookingapp.viewpods.fbgooglebtnViewpod
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment() : Fragment(), LoginDelegate {
    lateinit var btnLoginViewpod: fbgooglebtnViewpod
    lateinit var mLoginDelegate: PassDataDelegate

    companion object {
        const val KEY_HOME_DESCRIPTION = "KEY_HOME_DESCRIPTION"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mLoginDelegate = context as PassDataDelegate

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLoginViewpod = vpLoginSignUp as fbgooglebtnViewpod
        btnLoginViewpod.setUpLoginViewpod(this)

//        mLoginDelegate?.let {
//            btnLoginViewpod.setUpLoginViewpod(it)
//        }


//        view.btnConfirm.setOnClickListener {
//            val intent= Intent(activity,HomeActivity::class.java)
//            startActivity(intent)
//        }
    }
    override fun onTapLoginSignUpConfirm() {

        mLoginDelegate.onPassLoginData(
            txtEmailField.text.toString(),
            txtPasswordField.text.toString()
        )

    }


}