package com.jshelf.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.jshelf.themoviebookingapp.delegates.LoginDelegate
import kotlinx.android.synthetic.main.button_view_holder.view.*

class fbgooglebtnViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var loginDelegate: LoginDelegate
    lateinit var signUpDelegate: LoginDelegate
    override fun onFinishInflate() {

        super.onFinishInflate()

    }

    fun setUpLoginViewpod(loginDelegate: LoginDelegate) {

        btnConfirm.setOnClickListener {
            loginDelegate.onTapLoginSignUpConfirm()
        }
    }
}