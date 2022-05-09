package org.techtown.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.assignment.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        repositoryAdapter = RepositoryAdapter()
        binding.rcvRepository.adapter = repositoryAdapter

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제1"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제2"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제3"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제4"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제5"),
                RepositoryData("안드로이드 과제 레포지토리", "안드로이드 파트 과제6"),
                RepositoryData("IOS 과제 레포지토리", "IOS 파트 과제"),
                RepositoryData("웹 과제 레포지토리", "웹 파트 과제"),
                RepositoryData("기획 과제 레포지토리", "기획 파트 과제"),
                RepositoryData("서버 과제 레포지토리", "서버 파트 과제"),
                RepositoryData("디자인 과제 레포지토리", "디자인 파트 과제")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

}