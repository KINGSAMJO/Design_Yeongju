package org.techtown.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.databinding.ActivitySigninBinding

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

        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pwd = binding.etPwd.text.toString()

            emptyCheck = id.isEmpty() || pwd.isEmpty()
            val intent1 = Intent(this, HomeActivity::class.java)
            startActivity(intent1)

            if (!emptyCheck) {

                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnJoin.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent2)
        }
    }
}