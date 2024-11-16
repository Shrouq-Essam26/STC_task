package com.example.mysteryShopper.presentation.ui.activities.expandedView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysteryShopper.core.Resource
import com.example.mysteryShopper.data.model.CharacterModel
import com.example.mysteryShopper.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpandedViewViewModel @Inject constructor() :
    ViewModel() {

}