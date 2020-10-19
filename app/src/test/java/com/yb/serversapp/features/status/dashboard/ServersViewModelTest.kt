package com.yb.serversapp.features.status.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yb.serversapp.data.remote.repos.ServerStatusRepository
import com.yb.serversapp.data.testdata.ServersData
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.helpers.Resource
import com.yb.serversapp.helpers.ResourceStatus
import com.yb.serversapp.utils.getOrAwaitValue
import com.yb.serversapp.utils.isEqual
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ServersViewModelTest {

    private val statusRepo: ServerStatusRepository = mockk()
    private val io = Schedulers.trampoline()
    private val ui = Schedulers.trampoline()
    private lateinit var viewModel: ServersViewModel

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ServersViewModel(statusRepo, io, ui)
    }

    @Test
    fun `getServerStatuses given a success response, verify that live data is a success resource`() {
        //Given
        every { statusRepo.getServerStatuses() } returns (Single.just(emptyList()))

        //When
        viewModel.getServerStatuses()

        //Then
        val value = viewModel.serverStatuses.getOrAwaitValue()
        assertThat(value.status, `is`(ResourceStatus.SUCCESS))
    }

    @Test
    fun `getServerStatuses given a success response, verify that live data has correct data`() {
        //Given
        every { statusRepo.getServerStatuses() } returns (Single.just(ServersData.serversList))

        //When
        viewModel.getServerStatuses()

        //Then
        val value = viewModel.serverStatuses.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
        value.data?.forEachIndexed { index, serverStatus ->
            assert(serverStatus.isEqual(ServersData.serversList[index]))
        }
    }

    @Test
    fun `getServerStatuses given a error response, verify that live data is a error resource`() {
        //Given
        every { statusRepo.getServerStatuses() } returns (Single.error(Throwable()))

        //When
        viewModel.getServerStatuses()

        //Then
        val value = viewModel.serverStatuses.getOrAwaitValue()
        assertThat(value.status, `is`(ResourceStatus.ERROR))
    }

    @Test
    fun `getServerStatuses given a error response, verify that live data retains previous data`() {
        //Given
        every { statusRepo.getServerStatuses() } returns (Single.just(ServersData.serversList))
        viewModel.getServerStatuses()
        every { statusRepo.getServerStatuses() } returns (Single.error(Throwable()))

        //When
        viewModel.getServerStatuses()

        //Then
        val value = viewModel.serverStatuses.getOrAwaitValue()
        assertThat(value.status, `is`(ResourceStatus.ERROR))
        assertThat(value.data, `is`(notNullValue()))
        value.data?.forEachIndexed { index, serverStatus ->
            assert(serverStatus.isEqual(ServersData.serversList[index]))
        }
    }

    @Test
    fun `getServerStatuses, verify that live data sends a loading resource`() {
        //Given
        every { statusRepo.getServerStatuses() } returns (Single.error(Throwable()))
        val responses = mutableListOf<Resource<List<ServerStatus>>>()
        val observer = Observer<Resource<List<ServerStatus>>> { responses.add(it) }
        viewModel.serverStatuses.observeForever(observer)

        //When
        viewModel.getServerStatuses()

        //Then
        assertThat(responses[0].status, `is`(ResourceStatus.LOADING))
        assertThat(responses[1].status, `is`(ResourceStatus.ERROR))
        viewModel.serverStatuses.removeObserver(observer)
    }

}