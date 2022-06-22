package com.example.taghiveapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taghiveapp.adapter.TickerAdapter
import com.example.taghiveapp.data.TickerClickInterface
import com.example.taghiveapp.data.TickerResponse
import com.example.taghiveapp.data.TickerResponseItem
import com.example.taghiveapp.databinding.FragmentTickerTabBinding

class TickerTabFragment: Fragment() , TickerClickInterface {

    private var tickerAdapter: TickerAdapter? = null
    private var toGet: Boolean = false
    private var toPassQuote: String = ""
    private lateinit var fragmentTickerTabBinding : FragmentTickerTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        var toSelect = ""
        var mTickerResponse : TickerResponse? = null
        var mQuoteAssetArrayList : ArrayList<String>? = null
        fun newInstance(
            bundle: String,
            tickerResponse: TickerResponse,
            quoteAssetArrayList: ArrayList<String>?
        ): TickerTabFragment {
            toSelect = bundle
            mTickerResponse = tickerResponse
            mQuoteAssetArrayList = quoteAssetArrayList
            val fragment = TickerTabFragment()
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            if (!toSelect.isNullOrEmpty()) {
                    for (i in mQuoteAssetArrayList!!.indices) {
                        if (toSelect.equals(mQuoteAssetArrayList!!.get(i) , true)) {
                            toGet = true
                            toPassQuote = mQuoteAssetArrayList!!.get(i)
                            break
                        }
                    }

                if (toGet) {
                    when {
                        toSelect.equals(toPassQuote, true) -> {
                            var sortTickerResponse: List<TickerResponseItem> =
                                mTickerResponse!!.filter { s -> s.quoteAsset == toSelect }
                            tickerAdapter = TickerAdapter(sortTickerResponse , this)
                            fragmentTickerTabBinding.rvTicker?.layoutManager = LinearLayoutManager(context)
                            fragmentTickerTabBinding.rvTicker?.adapter = tickerAdapter
                            fragmentTickerTabBinding.rvTicker.visibility = View.VISIBLE
                        }
                        toSelect.equals(toPassQuote, true) -> {
                            var sortTickerResponse: List<TickerResponseItem> =
                                mTickerResponse!!.filter { s -> s.quoteAsset == toSelect }
                            tickerAdapter = TickerAdapter(sortTickerResponse , this)
                            fragmentTickerTabBinding.rvTicker?.layoutManager = LinearLayoutManager(context)
                            fragmentTickerTabBinding.rvTicker?.adapter = tickerAdapter
                            fragmentTickerTabBinding.rvTicker.visibility = View.VISIBLE
                        }
                        toSelect.equals(toPassQuote, true) -> {
                            var sortTickerResponse: List<TickerResponseItem> =
                                mTickerResponse!!.filter { s -> s.quoteAsset == toSelect }
                            tickerAdapter = TickerAdapter(sortTickerResponse , this)
                            fragmentTickerTabBinding.rvTicker?.layoutManager = LinearLayoutManager(context)
                            fragmentTickerTabBinding.rvTicker?.adapter = tickerAdapter
                            fragmentTickerTabBinding.rvTicker.visibility = View.VISIBLE
                            //fragmentTickerTabBinding.progress.visibility = View.GONE
                        }
                        toSelect.equals(toPassQuote, true) -> {
                            var sortTickerResponse: List<TickerResponseItem> =
                                mTickerResponse!!.filter { s -> s.quoteAsset == toSelect }
                            tickerAdapter = TickerAdapter(sortTickerResponse , this)
                            fragmentTickerTabBinding.rvTicker?.layoutManager = LinearLayoutManager(context)
                            fragmentTickerTabBinding.rvTicker?.adapter = tickerAdapter
                            fragmentTickerTabBinding.rvTicker.visibility = View.VISIBLE
                        }
                        else -> {
                            var sortTickerResponse: List<TickerResponseItem> =
                                mTickerResponse!!.filter { s -> s.quoteAsset == "usdt" }
                            tickerAdapter = TickerAdapter(sortTickerResponse , this)
                            fragmentTickerTabBinding.rvTicker?.layoutManager = LinearLayoutManager(context)
                            fragmentTickerTabBinding.rvTicker?.adapter = tickerAdapter
                            fragmentTickerTabBinding.rvTicker.visibility = View.VISIBLE
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_ticker_tab, container, false)
        fragmentTickerTabBinding = FragmentTickerTabBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSymbolClick(bundle: Bundle) {
        try {
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack("Ticker Fragment")?.apply {
                replace(R.id.flFragment, TickerDetailsFragment.newInstance(bundle))
                commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}