package org.techtown.assignment.ui.login

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.techtown.assignment.R
import org.techtown.assignment.databinding.ActivitySigninBinding
import org.techtown.assignment.ui.HomeActivity
import org.techtown.assignment.ui.login.viewmodel.SignInViewModel
import org.techtown.assignment.util.BaseActivity
import org.techtown.assignment.util.showToast

class SignInActivity :
    BaseActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    val viewModel: SignInViewModel by viewModels()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userId = it.data?.getStringExtra("userId") ?: ""
                val userPwd = it.data?.getStringExtra("userPwd") ?: ""
                binding.etId.setText(userId)
                binding.etPwd.setText(userPwd)
            }
        }

    override fun init() {
        binding.viewmodel = viewModel

        binding.btnJoin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        viewModel.successLogin.observe(this) {
            if (it) {
                showToast("${viewModel.id.value}님 안녕하세요.")
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            } else showToast("아이디/비밀번호를 확인해주세요.")
        }
    }
}