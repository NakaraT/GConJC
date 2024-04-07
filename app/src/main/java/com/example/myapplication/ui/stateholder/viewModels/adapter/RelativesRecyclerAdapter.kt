//package com.example.geneticcalc.ui.stateholder.viewModels.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ListAdapter
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.geneticcalc.data.database.entity.RelativesEntity
//import com.example.geneticcalc.databinding.ItemRecycleBinding
//import com.example.geneticcalc.ui.stateholder.OnRelativesListClickListener
//import com.example.geneticcalc.ui.stateholder.RelativesViewHolder
//
//
//class RelativesRecyclerAdapter(
//    private val onRelativesListItemListener: (Int) -> Unit
//) : ListAdapter<RelativesEntity, RelativesViewHolder>(RelativeDiffUtil()) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelativesViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemRecycleBinding.inflate(inflater, parent, false)
//        return RelativesViewHolder(binding, onRelativesListItemListener)
//    }
//
//    override fun onBindViewHolder(holder: RelativesViewHolder, position: Int) {
//        val relativeItem = getItem(position)
//        holder.bind(relativeItem)
//        holder.itemView.setOnClickListener {
//            onRelativesListItemListener(relativeItem.id)
//        }
//    }
//}