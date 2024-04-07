package com.example.geneticcalc.ui.stateholder

//import androidx.recyclerview.widget.RecyclerView
//import com.example.geneticcalc.R
//import com.example.geneticcalc.data.database.entity.RelativesEntity
//import com.example.geneticcalc.databinding.ItemRecycleBinding
//import com.example.myapplication.R
//
//
//class RelativesViewHolder(
//    binding: ItemRecycleBinding,
//    onRelativesListItemListener: (Int) -> Unit
//) : RecyclerView.ViewHolder(binding.getRoot()) {
//    private val binding: ItemRecycleBinding
//    private val onRelativesListItemListener: (Int) -> Unit
//
//    init {
//        this.binding = binding
//        this.onRelativesListItemListener = onRelativesListItemListener
//    }
//
//    fun bind(item: RelativesEntity) {
//        binding.getRoot()
//            .setOnClickListener { view -> onRelativesListItemListener(item.id) }
//        binding.relativeLogo.setImageResource(R.drawable.ic_baseline_man_2_24)
//        binding.relativesType.setText(item.relativesType)
//        //        binding.eyeColor.setText(item.getEyeColor());
//        binding.dateofBirth.setText(item.dateofBirth)
//        //        binding.bloodType.setText(item.getBloodType());
////        binding.hairColor.setText(item.getHairColor());
//    }
//}