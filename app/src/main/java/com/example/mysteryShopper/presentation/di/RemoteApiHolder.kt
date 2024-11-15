package com.example.mysteryShopper.presentation.di

import com.example.mysteryShopper.data.api.Api

class RemoteApiHolder {
    private var remoteApi: Api? = null
    fun getRemoteApi() = remoteApi
    fun setRemoteApi(value: Api) {
        this.remoteApi = value
    }
}