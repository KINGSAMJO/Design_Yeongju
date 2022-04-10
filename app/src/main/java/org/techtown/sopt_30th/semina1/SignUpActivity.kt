package org.techtown.sopt_30th.semina1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.sopt_30th.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var emptyCheck = false
    private var pwdCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.join.setOnClickListener {
            val name = binding.inputName.text.toString()
            val id = binding.inputID.text.toString()
            val pwd = binding.inputPWD.text.toString()
            val repwd = binding.inputREPWD.text.toString()

            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("userID", binding.inputID.text.toString())
            intent.putExtra("userPWD", binding.inputPWD.text.toString())
            setResult(RESULT_OK, intent)

            emptyCheck = name.isEmpty()||id.isEmpty()||pwd.isEmpty()||repwd.isEmpty()
            pwdCheck = pwd == repwd

            if(!emptyCheck && pwdCheck) {
                Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                if(emptyCheck){
                    Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                }
                else if(!pwdCheck && !emptyCheck){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}