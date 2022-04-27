package org.techtown.sopt_30th.seminar2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.sopt_30th.databinding.ActivitySampleRecyclerViewBinding

class SampleRecyclerView : AppCompatActivity() {

    private lateinit var sampleAdapter: SampleAdapter
    private lateinit var binding : ActivitySampleRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
    }

    private fun initAdapter(){
        sampleAdapter = SampleAdapter()
        binding.itemMain.adapter = sampleAdapter

        sampleAdapter.userList.addAll(
            listOf(
                UserData("이영주", "반갑습니다."),
                UserData("김세훈", "안녕하세요."),
                UserData("김아무개", "ㅁㄴㅇㄹ."),
                UserData("홍길동", "ㅂㅈㄷㄱ."),
                UserData("나까무라", "ㅋㅌㅊㅍ."),
                UserData("유병재", "ㅂㅈㄷㄱ.")
            )
        )
        sampleAdapter.notifyDataSetChanged()
    }
}