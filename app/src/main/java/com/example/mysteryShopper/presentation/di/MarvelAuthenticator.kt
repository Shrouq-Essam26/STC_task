package com.example.mysteryShopper.presentation.di

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class MarvelAuthenticator(private val publicKey: String, private val privateKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val ts = System.currentTimeMillis().toString()
        val hash = "$ts$privateKey$publicKey".md5()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun String.md5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}