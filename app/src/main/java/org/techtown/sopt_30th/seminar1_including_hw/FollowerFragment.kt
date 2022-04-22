package org.techtown.sopt_30th.seminar1_including_hw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.sopt_30th.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rcvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("이영주", "안드로이드"),
                FollowerData("김예현", "기획"),
                FollowerData("권용민", "안드로이드"),
                FollowerData("한수아", "IOS"),
                FollowerData("이종하", "기획"),
                FollowerData("권세훈", "서버")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

}

