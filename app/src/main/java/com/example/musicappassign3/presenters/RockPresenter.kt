package com.example.musicappassign3.presenters

import android.content.Context
import com.example.musicappassign3.model.RockItem
import com.example.musicappassign3.model.Rocks
import com.example.musicappassign3.rest.MusicService
import com.example.musicappassign3.rest.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RockPresenter(
    private var context: Context? = null,
    private var viewContract: RockViewContract? = null,
    private var networkUtils: NetworkUtils = NetworkUtils(context),
    private var disposable: CompositeDisposable = CompositeDisposable()
): RockPresenterContract {

    override fun checkNetwork(){
        networkUtils.registerForNetworkState()
    }

    override fun getRockTrack(){
        viewContract?.loadingRockTrack(true)

        networkUtils.networkState
            .subscribe(
                { netState -> if (netState) {
                    doNetworkCall()
                } else {
                    viewContract?.rockTrackError(Throwable("ERROR NO INTERNET CONNECTION"))
                } },
                { viewContract?.rockTrackError(it) }
            ).apply {
                disposable.add(this)
            }
    }

    override fun destroy(){
        networkUtils.unregisterFromNetworkState()
        context = null
        viewContract = null
        disposable.dispose()
    }

    private fun doNetworkCall() {
        MusicService.retrofitService.getRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> viewContract?.rockSuccess(response.rockItems) },
                { error -> viewContract?.rockTrackError(error) }
            ).apply {
                disposable.add(this)
            }
    }
}

interface RockViewContract{
    fun loadingRockTrack(isLoading: Boolean)
    fun rockSuccess(rockCollectionList: List<RockItem>)
    fun rockTrackError(throwable: Throwable)
}

interface RockPresenterContract{
    fun getRockTrack()
    fun destroy()
    fun checkNetwork()
}