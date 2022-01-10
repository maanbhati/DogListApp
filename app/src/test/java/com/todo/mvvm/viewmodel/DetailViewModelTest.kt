package com.todo.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.todo.mvvm.data.helper.ModelConverter
import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.repository.DogListRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: DogListRepository

    @MockK
    private lateinit var modelConverter: ModelConverter

    @MockK
    private lateinit var dogDetailsEntity: DogDetailsEntity

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DetailViewModel(repository, modelConverter)
    }

    @Test
    fun when_get_dog_image_called_verify_method_call_from_repository() = runBlockingTest {
        val dogName = "dogName"
        viewModel.getDogImage(dogName)

        verify { runBlockingTest { repository.getDogImage(dogName) } }
    }

    @Test
    fun when_update_dog_detail_called_verify_method_call_from_repository() = runBlockingTest {
        val dogName = "DogName"
        every { dogDetailsEntity.dogName } returns dogName

        viewModel.updateDogDetailData(dogDetailsEntity)

        verify { runBlockingTest { repository.deleteDogDetail(dogName) } }
        verify { runBlockingTest { repository.insertDogDetail(dogDetailsEntity) } }
    }
}