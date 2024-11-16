package com.example.mysteryShopper.presentation.ui.activities.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysteryShopper.core.Resource
import com.example.mysteryShopper.data.model.CharacterModel
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.domain.usecases.FetchSectionDetailsUseCase
import com.example.mysteryShopper.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val fetchSectionDetailsUseCase: FetchSectionDetailsUseCase) :
    ViewModel() {

    val comics = MutableLiveData<Resource<List<SectionModel>>>()

    val series = MutableLiveData<Resource<List<SectionModel>>>()

    val stories = MutableLiveData<Resource<List<SectionModel>>>()

    val events = MutableLiveData<Resource<List<SectionModel>>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchSection(resourceURI: String, sectionType: String) {
        viewModelScope.launch {
            try {
                val result = fetchSectionDetailsUseCase.build("$resourceURI/$sectionType")
                    .collect() { response ->
                        when (sectionType) {
                            "comics" -> comics.postValue(Resource.success(response))
                            "series" -> series.postValue(Resource.success(response))
                            "stories" -> stories.postValue(Resource.success(response))
                            "events" -> events.postValue(Resource.success(response))
                        }


                    }
            } catch (e: Throwable) {
                errorMessage.postValue(e.message)
                when (sectionType) {
                    "comics" -> comics.postValue(Resource.error(null , e.message.toString()))
                    "series" -> series.postValue(Resource.error(null , e.message.toString()))
                    "stories" -> stories.postValue(Resource.error(null , e.message.toString()))
                    "events" -> events.postValue(Resource.error(null , e.message.toString()))
                }            }

        }
    }
}