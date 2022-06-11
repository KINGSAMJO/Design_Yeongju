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

            emptyCheck = name.isEmpty() || id.isEmpty() || pwd.isEmpty()

            if (!emptyCheck) {
                joinNetwork()
                showToast("회원가입 완료!")
                finish()
            },
            onError = {
                showToast("회원가입에 실패하였습니다.")
            }
        )
    }
}