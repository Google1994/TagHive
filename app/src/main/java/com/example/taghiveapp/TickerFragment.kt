package com.example.taghiveapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.taghiveapp.adapter.TickerAdapter
import com.example.taghiveapp.data.TickerClickInterface
import com.example.taghiveapp.databinding.FragmentTickerBinding
import com.example.taghiveapp.data.TickerResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener


class TickerFragment : Fragment() , TickerClickInterface {


    private lateinit var viewModel: TickerViewModel
    private lateinit var fragmentTickerBinding: FragmentTickerBinding
    private var globalTickerResponse : TickerResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance() = TickerFragment()
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProviders.of(this)[TickerViewModel::class.java]
        viewModel.init()
        viewModel.getTicker()
        viewModel.getTickerResponseLiveData()?.observe(this,
            { tickerResponse ->
                if (tickerResponse != null) {
                    globalTickerResponse = tickerResponse
                    toCallApi(tickerResponse)
                }
            })

        fragmentTickerBinding.pullToRefresh.setOnRefreshListener(OnRefreshListener {
            if (!globalTickerResponse.isNullOrEmpty())
                toCallApi(globalTickerResponse!!)

            fragmentTickerBinding.pullToRefresh.setRefreshing(false)
        })
    }
    private var quoteAssetArrayList: ArrayList<String>? = null
    fun toCallApi(tickerResponse: TickerResponse) {
        try {
            if (quoteAssetArrayList == null)
                quoteAssetArrayList = arrayListOf()

            for (i in tickerResponse.indices) {
                if (quoteAssetArrayList.isNullOrEmpty())
                    quoteAssetArrayList?.add(tickerResponse[i].quoteAsset.toString())
                else {
                    if (!quoteAssetArrayList!!.contains(tickerResponse[i].quoteAsset))
                    {
                        quoteAssetArrayList?.add(tickerResponse[i].quoteAsset.toString())
                    }
                }
            }

            val viewPagerAdapter = ViewPagerAdapter(requireActivity())
            for (i in quoteAssetArrayList!!.indices) {
                viewPagerAdapter.add(TickerTabFragment.newInstance(quoteAssetArrayList!![0] , tickerResponse , quoteAssetArrayList), quoteAssetArrayList?.get(i))
            }
            fragmentTickerBinding.vpMatchDetails.adapter = viewPagerAdapter
            TabLayoutMediator(
                fragmentTickerBinding.tlMatchdetails, fragmentTickerBinding.vpMatchDetails
            ) { tab, position ->
                tab.text = viewPagerAdapter.getTabTitle(position)
            }.attach()

            for (i in 0 until fragmentTickerBinding.tlMatchdetails.tabCount) {
                val tab =
                    (fragmentTickerBinding.tlMatchdetails.getChildAt(0) as ViewGroup).getChildAt(
                        i
                    )
                val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                //  p.setMargins(30, 0, 30, 0)
                tab.requestLayout()
            }

            fragmentTickerBinding.vpMatchDetails.isUserInputEnabled = false

            fragmentTickerBinding.tlMatchdetails.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    TickerTabFragment.newInstance(tab?.text.toString(), tickerResponse , quoteAssetArrayList)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_ticker, container, false)
        fragmentTickerBinding = FragmentTickerBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TickerViewModel::class.java]
    }

    override fun onSymbolClick(bundle: Bundle) {

    }

}