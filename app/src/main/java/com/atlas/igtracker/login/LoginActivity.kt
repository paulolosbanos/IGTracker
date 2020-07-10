package com.atlas.igtracker.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.atlas.igtracker.R
import com.atlas.igtracker.http.GraphService
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity() {

    private val callbackManager: CallbackManager by inject()
    private val service: GraphService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginManager.getInstance().logInWithReadPermissions(this, mutableListOf("public_profile", "instagram_basic", "pages_show_list", "user_posts", "business_management", "pages_read_engagement"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.e("onSuccess", loginResult.accessToken.token)
                    service
                        .accounts(loginResult.accessToken.token)
                        .flatMap { service.businessAccounts(it.data.first().id,"instagram_business_account", loginResult.accessToken.token) }
                        .subscribe {
                            Log.e("bus ac", "${it.instagram_business_account.id}")
                        }
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    Log.e("onError", exception.toString())
                }
            })


        login_button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
