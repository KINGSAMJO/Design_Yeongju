package org.techtown.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
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

        childFragmentManager.beginTransaction().add(R.id.fcv_profile, fragment1).commit()

        binding.btnFollower.setOnClickListener {
            btn_follower?.isSelected = btn_follower?.isSelected != true
            //조건을 어떻게 달아줄까..
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_profile, fragment1)
            transaction.commit()
        }
        binding.btnRepository.setOnClickListener {
            btn_repository?.isSelected = btn_repository?.isSelected != true
            //조건을 어떻게 달아줄까..
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_profile, fragment2)
            transaction.commit()
        }

    }
}