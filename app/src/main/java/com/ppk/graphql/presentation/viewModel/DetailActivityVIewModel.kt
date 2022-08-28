package com.ppk.graphql.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.domain.interactor.GetLaunchListUseCase
import com.ppk.graphql.presentation.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class DetailActivityVIewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var useCase: GetLaunchListUseCase

    private val myCharacter by lazy { MutableLiveData<ViewState<ApolloResponse<CharacterQuery.Data>>>() }
    val character: LiveData<ViewState<ApolloResponse<CharacterQuery.Data>>>
        get() = myCharacter

    fun queryCharacter(id: String) = viewModelScope.launch {
        myCharacter.postValue(ViewState.Loading())
        try {
            val response = useCase.getCharacter(id)
            myCharacter.postValue(ViewState.Success(response))
        } catch (ae: ApolloException) {
            myCharacter.postValue(ViewState.Error("Error fetching character"))
        }
    }


}