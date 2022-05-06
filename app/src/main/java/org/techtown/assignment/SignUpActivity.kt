package org.techtown.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var emptyCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJoin2.setOnClickListener {
            val name = binding.etName.text.toString()
            val id = binding.etJoinid.text.toString()
            val pwd = binding.etJoinpwd.text.toString()

            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("userId", binding.etJoinid.text.toString())
            intent.putExtra("userPwd", binding.etJoinpwd.text.toString())

            emptyCheck = name.isEmpty() || id.isEmpty() || pwd.isEmpty()

            if (!emptyCheck) {
                Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}