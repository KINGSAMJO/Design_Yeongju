package org.techtown.assignment.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.techtown.assignment.ui.login.model.RequestSignUp
import org.techtown.assignment.ui.login.model.ServiceCreator
import org.techtown.assignment.util.enqueueUtil

class SignUpViewModel : ViewModel() {
    private var _name = MutableLiveData<String>()
    val name get() = _name
    private var _id = MutableLiveData<String>()
    val id get() = _id
    private var _pwd = MutableLiveData<String>()
    val pwd get() = _pwd
    private val _successJoin = MutableLiveData<Boolean>()
    val successJoin get() = _successJoin
    private val _fillInfo = MutableLiveData<Boolean>()
    val fillInfo get() = _fillInfo

    fun onClickButton() {
        if (name.value.isNullOrEmpty() || id.value.isNullOrEmpty() || pwd.value.isNullOrEmpty()) {
            _fillInfo.value = false
            _successJoin.value = false
            return
        }

        val call = ServiceCreator.soptService.postSignUp(
            RequestSignUp(
                name.value!!,
                id.value!!,
                pwd.value!!
            )
        )
        call.enqueueUtil(
            onSuccess = {
                _successJoin.value = true
            },
            onError = {
                _successJoin.value = false
            }
        )
    }
}