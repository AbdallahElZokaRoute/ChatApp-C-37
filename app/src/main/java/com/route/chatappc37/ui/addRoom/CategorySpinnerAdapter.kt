package com.route.chatappc37.ui.addRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ItemCategorySpinnerBinding
import com.route.chatappc37.model.Category

class CategorySpinnerAdapter(val items: List<Category>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Recycler -> OnCreateViewHolder, OnBindViewHolder
        var myView: View? = convertView
        var categoryViewHolder: CategoryViewHolder
        if (myView == null) {
            val viewBinding: ItemCategorySpinnerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context!!),
                R.layout.item_category_spinner,
                parent, false
            )
            myView = viewBinding.root
            categoryViewHolder = CategoryViewHolder(viewBinding)
            myView.tag = categoryViewHolder
        } else {
            categoryViewHolder = myView.tag as CategoryViewHolder
        }
        categoryViewHolder.bind(items[position])

        return myView
    }

    class CategoryViewHolder(val viewBinding: ItemCategorySpinnerBinding) {
        fun bind(category: Category) {
            viewBinding.categoryName.text = category.name
            viewBinding.categoryImage.setImageResource(category.imageResId ?: R.drawable.sports)
        }
    }

}