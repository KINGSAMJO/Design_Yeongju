package org.techtown.sopt_30th.semina1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.sopt_30th.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}