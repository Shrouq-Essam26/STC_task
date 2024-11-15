package com.example.mysteryShopper.presentation.di


import com.example.mysteryShopper.data.source.PreferencesHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class MyAuthenticator @Inject constructor(
    private val remoteApiHolder: RemoteApiHolder,
    private val sharedPref: PreferencesHelper
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val remoteApi = remoteApiHolder.getRemoteApi()
        remoteApi?.let {
            /*
            * simulate expired PW
            * sharedPref.password = "passwordDEwWMU6fRXrbQdTYmqF/9NHg9ZzOO94dJFgNl0ZNYDS4lNf7vi6onmJdHIhHTtUzA6TKiD0IEwMcCR1UgByBdhjbyBHh9cNQN7DpFRDmYBQAgHYOFhKZwOfWbpP2g5AljT3dzHtRwQ0oqtovhTCPHXzfaufZY806UoRB+kOzKbA="
            */
//            val loginResponse = it.loginPost(
//                LoginPostRequest(
//                    password = sharedPref.password,
//                    username = sharedPref.userName
//                )
//            ).blockingGet()

//            sharedPref.accessToken = loginResponse!!.token
            return response.request.newBuilder()
                .addHeader("Authorization", "Bearer ${sharedPref.accessToken}")
                .build()
        }
        return null
    }
}
// 76990655