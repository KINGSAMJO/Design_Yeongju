package org.techtown.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.databinding.ActivitySigninBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private var emptyCheck = false

    private lateinit var binding: ActivitySigninBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userId = it.data?.getStringExtra("userId") ?: ""
                val userPwd = it.data?.getStringExtra("userPwd") ?: ""
                binding.etId.setText(userId)
                binding.etPwd.setText(userPwd)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }


    private fun initEvent() {
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

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(
                        this@SignInActivity,
                        "${data?.name}님 반갑습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                } else Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest", "Error:$t")
            }
        })
    }
}