package com.example.taghiveapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.taghiveapp.databinding.FragmentTickerDetailsBinding
import com.example.taghiveapp.utils.nulltodashChecker

class TickerDetailsFragment : Fragment() , View.OnClickListener{

    private lateinit var fragmentTickerDetailsBinding: FragmentTickerDetailsBinding

    companion object {
        fun newInstance(bundle: Bundle = Bundle()): TickerDetailsFragment {
            val fragment = TickerDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: TickerDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            viewModel = ViewModelProviders.of(this)[TickerDetailsViewModel::class.java]
            viewModel.init()
            viewModel.getIndividualTicker(arguments?.getString("symbol"))

            viewModel.getIndividualTickerResponseLiveData()?.observe(this,
                { tickerDetailsResponse ->
                    if (tickerDetailsResponse != null) {
                        fragmentTickerDetailsBinding.progress.visibility = View.GONE
                        if (!tickerDetailsResponse.baseAsset.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvBaseAsset.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvBaseAsset.text = "Base Asset : - " + tickerDetailsResponse.baseAsset.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvBaseAsset.visibility = View.GONE

                        if (!tickerDetailsResponse.quoteAsset.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvQuote.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvQuote.text = "Quote Asset : - " +  tickerDetailsResponse.quoteAsset.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvQuote.visibility = View.GONE

                        if (!tickerDetailsResponse.symbol.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvSymbol.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvSymbol.text = "Symbol : - " + tickerDetailsResponse.symbol.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvSymbol.visibility = View.GONE

                        if (!tickerDetailsResponse.openPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvOpenprice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvOpenprice.text = "Open Price : - " + tickerDetailsResponse.openPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvOpenprice.visibility = View.GONE

                        if (!tickerDetailsResponse.lowPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvLowprice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvLowprice.text = "Low Price : - " + tickerDetailsResponse.lowPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvLowprice.visibility = View.GONE

                        if (!tickerDetailsResponse.highPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvHighprice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvHighprice.text = "High Price : - " + tickerDetailsResponse.highPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvHighprice.visibility = View.GONE

                        if (!tickerDetailsResponse.lastPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvLastprice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvLastprice.text = "Last Price : - " + tickerDetailsResponse.lastPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvLastprice.visibility = View.GONE

                        if (!tickerDetailsResponse.volume.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvVolume.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvVolume.text = "Volume : - " + tickerDetailsResponse.volume.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvVolume.visibility = View.GONE

                        if (!tickerDetailsResponse.bidPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvBidPrice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvBidPrice.text = "Bid Price : - " + tickerDetailsResponse.bidPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvBidPrice.visibility = View.GONE

                        if (!tickerDetailsResponse.askPrice.isNullOrEmpty()) {
                            fragmentTickerDetailsBinding.tvAskPrice.visibility = View.VISIBLE
                            fragmentTickerDetailsBinding.tvAskPrice.text = "Ask Price : - " + tickerDetailsResponse.askPrice.nulltodashChecker()
                        }
                        else
                            fragmentTickerDetailsBinding.tvAskPrice.visibility = View.GONE

                    }
                    else {
                        fragmentTickerDetailsBinding.progress.visibility = View.GONE
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_ticker_details, container, false)
        fragmentTickerDetailsBinding = FragmentTickerDetailsBinding.bind(view)
        fragmentTickerDetailsBinding.tvSymbolName.text = arguments?.getString("symbol")
        fragmentTickerDetailsBinding.progress.visibility = View.VISIBLE
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TickerDetailsViewModel::class.java)
    }

    override fun onClick(view: View?) {
    }

}