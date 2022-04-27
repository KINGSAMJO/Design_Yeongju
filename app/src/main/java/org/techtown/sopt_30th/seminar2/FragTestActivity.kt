package org.techtown.sopt_30th.seminar2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.sopt_30th.R
import org.techtown.sopt_30th.databinding.ActivityFragTestBinding

class FragTestActivity : AppCompatActivity() {
    private var position = FIRST_POSITION
    private lateinit var binding: ActivityFragTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        val fragment1 = FragTest1()
        val fragment2 = FragTest2()

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()

        binding.btnFragment.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
                FIRST_POSITION -> {
                    transaction.replace(R.id.fragment_main, fragment2)
                    position = SECOND_POSITION
                }
                SECOND_POSITION -> {
                    transaction.replace(R.id.fragment_main, fragment1)
                    position = FIRST_POSITION
                }
            }
            transaction.commit()
        }

    }
    companion object {
        const val FIRST_POSITION = 1
        const val SECOND_POSITION = 2
    }
}

