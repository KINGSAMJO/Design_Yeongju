package org.techtown.assignment.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.techtown.assignment.R
import org.techtown.assignment.databinding.ActivitySigninBinding
import org.techtown.assignment.ui.HomeActivity
import org.techtown.assignment.ui.login.model.RequestSignIn
import org.techtown.assignment.ui.login.model.ServiceCreator
import org.techtown.assignment.util.BaseActivity
import org.techtown.assignment.util.enqueueUtil
import org.techtown.assignment.util.showToast

class SignInActivity : BaseActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    private var emptyCheck = false

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
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pwd = binding.etPwd.text.toString()

            emptyCheck = id.isEmpty() || pwd.isEmpty()

            if (!emptyCheck) {
                loginNetwork()
            } else Toast.makeText(this, "정보를 모두 기입해주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.btnJoin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPwd.text.toString(),
        )

        val call = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueueUtil(
            onSuccess = {
                val data = it?.data
                showToast("${data?.name}님 반갑습니다!")
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            },
            onError = {
                showToast("아이디/비밀번호를 확인해주세요.")
            }
        )
    }
}