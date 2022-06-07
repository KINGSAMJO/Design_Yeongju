package org.techtown.assignment.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.assignment.R
import org.techtown.assignment.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        initTransactionEvent()
        return binding.root

    }

    private fun initTransactionEvent() {
        val fragment1 = FollowerFragment()
        val fragment2 = RepositoryFragment()
        binding.btnFollower.isSelected = true

        childFragmentManager.beginTransaction().add(R.id.fcv_profile, fragment1).commit()

        binding.btnFollower.setOnClickListener {
            binding.btnFollower.isSelected = true
            binding.btnRepository.isSelected = false
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_profile, fragment1)
            transaction.commit()
        }
        binding.btnRepository.setOnClickListener {
            binding.btnRepository.isSelected = true
            binding.btnFollower.isSelected = false
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_profile, fragment2)
            transaction.commit()
        }

    }
}