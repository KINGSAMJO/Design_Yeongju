package org.techtown.assignment.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.assignment.ui.profile.adapter.FollowerAdapter
import org.techtown.assignment.ui.profile.data.FollowerData
import org.techtown.assignment.databinding.FragmentFollowerBinding

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
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자"),
                FollowerData("이영주", "안드로이드 개발자")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

}