package org.techtown.sopt_30th.seminar1_including_hw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.sopt_30th.databinding.RepositoryListBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.SampleViewHolder>() {
    val repositoryList = mutableListOf<RepositoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val binding =
            RepositoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    override fun getItemCount(): Int = repositoryList.size

    class SampleViewHolder(
        private val binding: RepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepositoryData) {
            binding.tvRepositoryName.text = data.repositoryName
            binding.tvRepositoryPart.text = data.repositoryPart
        }
    }
}
