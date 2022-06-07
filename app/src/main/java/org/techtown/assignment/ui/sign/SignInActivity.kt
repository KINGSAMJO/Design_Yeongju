package org.techtown.assignment.ui.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.assignment.model.RequestSignIn
import org.techtown.assignment.model.ServiceCreator
import org.techtown.assignment.databinding.ActivitySigninBinding
import org.techtown.assignment.ui.HomeActivity
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

    fun <ResponseType> Call<ResponseType>.enqueueUtil(
        onSuccess: (ResponseType) -> Unit,
        onError: ((stateCode: Int) -> Unit)? = null
    ){
        this.enqueue(object: Callback<ResponseType>{
            override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>){
                if(response.isSuccessful){
                    onSuccess.invoke(response.body()?:return)
                }else{
                    onError?.invoke(response.code())
                }
            }
            override fun onFailure(call: Call<ResponseType>, t:Throwable){
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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