package com.example.mysteryShopper.presentation.ui.activities.details

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
class DetailsViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {
    private val _characters = MutableLiveData<Resource<List<CharacterModel>>>()
    val characters: LiveData<Resource<List<CharacterModel>>> = _characters

    var currentPage = 0
    var isLoading = false
    private val _allCharacters = mutableListOf<CharacterModel>()

    val errorMessage = MutableLiveData<String>()

//    fun loadCharacters(limit: Int = 20) {
//        if (isLoading) return // Prevent duplicate calls while loading
//
//        isLoading = true
//        val offset = currentPage * limit // Calculate offset for pagination
//
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                // Trigger loading state
//                if (currentPage == 0) _characters.postValue(Resource.loading(null))
//
//                getCharactersUseCase.build(limit, offset).collect { response ->
//                    response.data.results.let { newCharacters ->
//                        _allCharacters.addAll(newCharacters)
//                        _characters.postValue(Resource.success(_allCharacters))
//                    }
//
//                    // Pagination state update
//                    currentPage++
//                    isLoading = false
//                }
//            } catch (exception: Throwable) {
//                errorMessage.postValue(exception.message)
//                _characters.postValue(
//                    Resource.error(data = _allCharacters, message = exception.message ?: "Error")
//                )
//                isLoading = false
//            }
//        }
//    }
}