package com.mazharulsabbir.paymentgateway

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazharulsabbir.paymentgateway.data.model.Desc
import kotlinx.android.synthetic.main.item_payment_option.view.*

class PaymentMethodsAdapter :
    ListAdapter<Desc, PaymentMethodsAdapter.ViewHolder>(DiffUtilCallback()) {
    private var onItemClickListener: OnItemClickListener? = null

    fun onItemClick(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Desc>() {
        override fun areItemsTheSame(
            oldItem: Desc,
            newItem: Desc
        ): Boolean {
            return oldItem.type == newItem.type && oldItem.rFlag == newItem.rFlag && oldItem.redirectGatewayURL == newItem.redirectGatewayURL
        }

        override fun areContentsTheSame(
            oldItem: Desc,
            newItem: Desc
        ): Boolean {
            return oldItem.type == newItem.type && oldItem.rFlag == newItem.rFlag
                    && oldItem.redirectGatewayURL == newItem.redirectGatewayURL
                    && oldItem.gw == newItem.gw && oldItem.name == newItem.name
                    && oldItem.logo == newItem.logo

        }

    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view
        fun bind(item: Desc) {
            view.name.text = item.name.toString()
            Glide.with(view.context).load(item.logo).into(view.logo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_payment_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
        holder.root.setOnClickListener {
            onItemClickListener?.let {
                if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(holder.adapterPosition)
                }
            }
        }
    }

}