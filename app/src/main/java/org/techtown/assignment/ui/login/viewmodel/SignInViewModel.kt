package org.techtown.assignment.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.techtown.assignment.ui.login.model.RequestSignIn
import org.techtown.assignment.ui.login.model.ServiceCreator
import org.techtown.assignment.util.enqueueUtil

class SignInViewModel : ViewModel() {
    private var _id = MutableLiveData<String>()
    val id get() = _id
    private var _pwd = MutableLiveData<String>()
    val pwd get() = _pwd
    private val _successLogin = MutableLiveData<Boolean>()
    val successLogin get() = _successLogin

    fun onClickButton() {
        if (id.value.isNullOrEmpty() || pwd.value.isNullOrEmpty()) {
            _successLogin.value = false
            return
        }

        val call = ServiceCreator.soptService.postLogin(RequestSignIn(id.value!!, pwd.value!!))
        call.enqueueUtil(
            onSuccess = {
                _successLogin.value = true
            },
            onError = {
                _successLogin.value = false
            }
        )
    }
}