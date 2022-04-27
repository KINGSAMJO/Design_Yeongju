package org.techtown.sopt_30th.seminar1_including_hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import org.techtown.sopt_30th.R
import org.techtown.sopt_30th.databinding.ActivitySigninBinding


class SignInActivity : AppCompatActivity() {
    var emptyCheck = false

    private lateinit var binding: ActivitySigninBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val userID = it.data?.getStringExtra("userID") ?: ""
                val userPWD = it.data?.getStringExtra("userPWD") ?: ""
                binding.inputID.setText(userID)
                binding.inputPWD.setText(userPWD)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        binding.signIn = this

        binding.login.setOnClickListener {
            val id = binding.inputID.text.toString()
            val pwd = binding.inputPWD.text.toString()

            emptyCheck = id.isEmpty() || pwd.isEmpty()

            if(!emptyCheck){
                var intent1 = Intent(this, HomeActivity::class.java)
                startActivity(intent1)
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
        binding.join.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent2)
        }
    }
}