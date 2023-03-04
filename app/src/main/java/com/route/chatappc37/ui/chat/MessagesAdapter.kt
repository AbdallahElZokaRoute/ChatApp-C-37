package com.route.chatappc37.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ItemReceivedMessageBinding
import com.route.chatappc37.databinding.ItemSendMessageBinding
import com.route.chatappc37.model.DataUtils
import com.route.chatappc37.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MessagesAdapter(var messages: MutableList<Message>?) : Adapter<ViewHolder>() {
    val SENT = 1
    val RECEIVED = 2
    override fun getItemViewType(position: Int): Int {

        return if (messages?.get(position)?.senderId == DataUtils.user?.id) {
            SENT
        } else {
            RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == SENT) {
            val itemBinding: ItemSendMessageBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_send_message,
                    parent, false
                )
            SentMessageViewHolder(itemBinding)

        } else {
            val itemBinding: ItemReceivedMessageBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_received_message,
                    parent, false
                )
            ReceivedViewHolder(itemBinding)
        }
    }

    override fun getItemCount(): Int {
        return messages?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = messages?.get(position)
        if (holder is SentMessageViewHolder) {
            holder.bind(message = item!!)
        } else if (holder is ReceivedViewHolder) {
            holder.bind(message = item!!)
        }
//        val type = getItemViewType(position)
//        if (type == SENT) {
//            val viewHolder = (holder as SentMessageViewHolder)
//            viewHolder.bind(item!!)
//        } else {
//            val viewHolder = (holder as ReceivedViewHolder)
//            viewHolder.bind(item!!)
//        }
    }

    fun receivedNewMessage(message: Message) {
        messages?.add(message)
        notifyItemInserted(itemCount+1)

    }

    fun updateData(items: MutableList<Message>?) {
        this.messages = items
        notifyDataSetChanged()
    }

    class SentMessageViewHolder(val sentMessageBinding: ItemSendMessageBinding) :
        ViewHolder(sentMessageBinding.root) {
        fun bind(message: Message) {
            sentMessageBinding.messageContent.text = message.content
            val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val date = simpleDateFormat.format(Date(message.time!!))
            sentMessageBinding.messageDate.text = date
            sentMessageBinding.executePendingBindings()
        }

    }

    class ReceivedViewHolder(val receivedMessageBinding: ItemReceivedMessageBinding) :
        ViewHolder(receivedMessageBinding.root) {

        fun bind(message: Message) {
            receivedMessageBinding.messageContent.text = message.content
            val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val date = simpleDateFormat.format(Date(message.time!!))
            receivedMessageBinding.messageDate.text = date
            receivedMessageBinding.executePendingBindings()

        }
    }


}