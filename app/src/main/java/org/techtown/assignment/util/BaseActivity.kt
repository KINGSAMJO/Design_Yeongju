package org.techtown.assignment.util

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : ViewDataBinding, VM: ViewModel>(@LayoutRes private val layoutRes: Int) :
    AppCompatActivity() {
    protected lateinit var binding: T
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 초기화된 layoutResId로 DataBinding 객체 생성
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.apply{
            // LiveData를 사용하기 위한 lifecycleOwner 지정
            binding.lifecycleOwner = this@BaseActivity
        }
        init()
    }

    protected abstract fun init()
}