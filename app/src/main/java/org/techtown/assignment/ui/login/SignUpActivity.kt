package org.techtown.assignment.ui.login

import android.content.Intent
import androidx.activity.viewModels
import org.techtown.assignment.R
import org.techtown.assignment.databinding.ActivitySignupBinding
import org.techtown.assignment.ui.login.viewmodel.SignUpViewModel
import org.techtown.assignment.util.BaseActivity
import org.techtown.assignment.util.showToast

class SignUpActivity :
    BaseActivity<ActivitySignupBinding, SignUpViewModel>(R.layout.activity_signup) {
    override val viewModel: SignUpViewModel by viewModels()

    override fun init() {
        binding.viewmodel = viewModel

        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra("userId", binding.etJoinid.text.toString())
        intent.putExtra("userPwd", binding.etJoinpwd.text.toString())

        viewModel.fillInfo.observe(this) {
            if (!it) {
                showToast("입력되지 않은 정보가 있습니다.")
            }
        }
        viewModel.successJoin.observe(this) {
            if (it) {
                showToast("회원가입 완료!")
                finish()
            } else showToast("중복된 아이디가 있습니다.")
        }
    }
}