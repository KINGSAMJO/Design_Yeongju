package org.techtown.assignment.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.ui.login.model.RequestSignUp
import org.techtown.assignment.ui.login.model.ServiceCreator
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

    fun <ResponseType> Call<ResponseType>.enqueueUtil(
        onSuccess: (ResponseType) -> Unit,
        onError: ((stateCode: Int) -> Unit)? = null
    ) {
        this.enqueue(object : Callback<ResponseType> {
            override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body() ?: return)
                } else {
                    onError?.invoke(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun joinNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            id = binding.etJoinid.text.toString(),
            password = binding.etJoinpwd.text.toString()
        )

        val call = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueueUtil(
            onSuccess = {
                val data = it?.data
                showToast("회원가입 완료!")
                finish()
            },
            onError = {
                showToast("회원가입에 실패하였습니다.")
            }
        )
    }
}