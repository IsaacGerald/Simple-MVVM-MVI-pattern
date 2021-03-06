package com.example.mvvm_mvirepopatternwithhilt.ui

import androidx.lifecycle.*
import com.example.mvvm_mvirepopatternwithhilt.model.Blog
import com.example.mvvm_mvirepopatternwithhilt.repository.MainRepository
import com.example.mvvm_mvirepopatternwithhilt.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _dataState = MutableLiveData<DataState<List<Blog>>>()

    val dataState: LiveData<DataState<List<Blog>>>
    get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogsEvent ->{
                    repository.getBlog()
                        .onEach { dataState ->
                             _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    //who cares
                }
            }
        }
    }


}

sealed class MainStateEvent{

    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()

}