package com.route.chatappc37.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ItemRoomBinding
import com.route.chatappc37.model.Category
import com.route.chatappc37.model.Room

class RoomsAdapter(var items: List<Room?>?) : Adapter<RoomsAdapter.RoomsViewHolder>() {

    var onRoomClickedListener: OnRoomClickedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val binding: ItemRoomBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_room,
            parent,
            false
        )
        return RoomsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item!!)
        onRoomClickedListener?.let { onClicked ->
            holder.itemView.setOnClickListener {
                onClicked.onRoomClicked(item, position)
            }
        }
    }

    fun updateData(rooms: List<Room>) {
        items = rooms
        notifyDataSetChanged()
    }

    class RoomsViewHolder(val itemBinding: ItemRoomBinding) : ViewHolder(itemBinding.root) {
        fun bind(room: Room) {
            itemBinding.roomName.text = room.name
            itemBinding.roomImage.setImageResource(
                Category.fromId(room.categoryId!!).imageResId ?: R.drawable.sports
            )
            itemBinding.executePendingBindings()

        }

    }


}

interface OnRoomClickedListener {
    fun onRoomClicked(room: Room, position: Int)

}