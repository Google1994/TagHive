package com.example.taghiveapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taghiveapp.R
import com.example.taghiveapp.data.TickerClickInterface
import com.example.taghiveapp.data.TickerResponseItem

class TickerAdapter(val tickerResponse: List<TickerResponseItem>, val tickerInterface: TickerClickInterface) : RecyclerView.Adapter<TickerAdapter.ViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(inflater.inflate(R.layout.row_ticker, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSymbol?.text = tickerResponse[position].symbol
        holder.tvBaseAsset?.text = "/"  + tickerResponse[position].baseAsset
        holder.tvBidPrice?.text = tickerResponse[position].bidPrice

        holder.llSymbol?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("symbol" , tickerResponse[position].symbol)
            tickerInterface.onSymbolClick(bundle)
        }
    }

    override fun getItemCount(): Int {
        return tickerResponse.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onClick(p0: View?) {
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var tvSymbol: TextView? = null
        var tvBaseAsset: TextView? = null
        var tvBidPrice: TextView? = null
        var llSymbol: RelativeLayout? = null

        init {
            tvSymbol = view.findViewById(R.id.tvSymbol)
            tvBaseAsset = view.findViewById(R.id.tvBaseAsset)
            tvBidPrice = view.findViewById(R.id.tvBidPrice)
            llSymbol = view.findViewById(R.id.llSymbol)
        }
    }
}