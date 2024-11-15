package com.example.mysteryShopper.presentation.ui.activities.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysteryShopper.core.Resource
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import com.example.mysteryShopper.domain.usecases.GetAppConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getAppConfigUseCase: GetAppConfigUseCase) :
    ViewModel() {
    val config = MutableLiveData<Resource<AppConfigEntity>>()
    fun getConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            config.postValue(Resource.loading(data = null))
            try {
                getAppConfigUseCase.build().collect {
                    config.postValue(Resource.success(data = it.data))
                }
            } catch (exception: Throwable) {
                config.postValue(
                    Resource.error(data = null, message = "error")
                )
            }
        }
    }
}