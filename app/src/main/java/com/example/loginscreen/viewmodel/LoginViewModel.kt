package com.example.loginscreen.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginscreen.core.appconstant.AppConstant
import com.example.loginscreen.core.utils.DateUtility
import com.example.loginscreen.core.utils.GovernmentFilesUtility
import com.example.loginscreen.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    var activateNextButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val panNumber: MutableLiveData<String> = MutableLiveData("")

    init {
        fetchEntries()
    }

    fun validateDateOfBirth(date: String?, month: String?, year: String?): Boolean {
        if (date?.isBlank() == true || month?.isBlank() == true || year?.isBlank() == true)
            return false
        val dateString = date.toString() + "/" + month.toString() + "/" + year.toString()
        return DateUtility.validate(dateString)
    }

    fun validatePAN(@NonNull panNumber: String): Boolean {
        if (panNumber.isBlank())
            return false

        return GovernmentFilesUtility.validatePAN(panNumber)
    }

    fun saveEntries(panNumber: String) {
        viewModelScope.launch {
            appRepository.saveValuesInDataStore(AppConstant.PAN_NUMBER, panNumber);
        }
    }

    fun fetchEntries() {
        viewModelScope.launch {
            val temp = appRepository.fetchValuesFromDataStore(AppConstant.PAN_NUMBER);
            temp?.let { panNumber.postValue(it) }
        }
    }
}