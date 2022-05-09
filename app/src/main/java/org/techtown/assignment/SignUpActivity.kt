package org.techtown.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                joinNetwork()

            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun joinNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            id = binding.etJoinid.text.toString(),
            password = binding.etJoinpwd.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(this@SignUpActivity, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                    finish()
                } else Toast.makeText(this@SignUpActivity, "회원가입 실패!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest", "Error:$t")
            }
        })
    }
}