package com.anderson.mysubscribers.ui.subscriberList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anderson.mysubscribers.data.db.entity.SubscriberEntity
import com.anderson.mysubscribers.databinding.SubscriberItemBinding


class SubscriberListAdapter(private val subscribers: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null

    // no onCreateViewHolder e para inflar o layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        return SubscriberListViewHolder(
            SubscriberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    inner class SubscriberListViewHolder(private val binding: SubscriberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val textViewSubscriberName: TextView = binding.textSubscriberName
        private val textViewSubscriberEmail: TextView = binding.textSubscriberEmail

        fun bindView(subscriber: SubscriberEntity) {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.email

            itemView.setOnClickListener {
                onItemClick?.invoke(subscriber)
            }
        }

    }
}