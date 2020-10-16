package com.yb.serversapp.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.domain.repos.IServerStatusRepository
import com.yb.serversapp.helpers.DisposingViewModel
import com.yb.serversapp.helpers.Resource
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    private val statusRepo: IServerStatusRepository,
    @Named("io") private val io: Scheduler,
    @Named("ui") private val ui: Scheduler,
) : DisposingViewModel() {

    private val _serverStatuses = MutableLiveData<Resource<List<ServerStatus>>>()
    val serverStatuses : LiveData<Resource<List<ServerStatus>>> = _serverStatuses

    init {
        getServerStatuses()
    }

    fun getServerStatuses() {
        statusRepo.getServerStatuses()
            .doOnSubscribe { _serverStatuses.postValue(Resource.loading(_serverStatuses.value?.data)) }
            .subscribeOn(io)
            .observeOn(ui)
            .subscribeBy (
                onError = {
                    _serverStatuses.value = Resource.error(data = _serverStatuses.value?.data, error = it)
                },
                onSuccess = {
                    Timber.d("Statuses received successfully = ${it.size}")
                    _serverStatuses.value = Resource.success(it)
                }
            ).addTo(disposables)
    }

}