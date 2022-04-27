package org.techtown.sopt_30th.seminar2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.sopt_30th.databinding.ItemSampleListBinding

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {
    val userList = mutableListOf<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val binding =
            ItemSampleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class SampleViewHolder(
        private val binding: ItemSampleListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }
}
