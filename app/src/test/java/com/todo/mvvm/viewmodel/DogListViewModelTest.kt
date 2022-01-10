package com.todo.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.todo.mvvm.data.helper.ModelConverter
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.mvvm.repository.DogListRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: DogListRepository

    @MockK
    private lateinit var modelConverter: ModelConverter

    @MockK
    private lateinit var dogListEntity: DogListEntity

    private lateinit var viewModel: DogListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DogListViewModel(repository, modelConverter)
    }

    @Test
    fun when_get_dog_list_called_verify_method_call_from_repository() = runBlockingTest {
        viewModel.getDogList()

        verify { runBlockingTest { repository.getDogList() } }
    }

    @Test
    fun when_update_dog_list_called_verify_method_call_from_repository() = runBlockingTest {
        viewModel.updateDogListData(dogListEntity)

        verify { runBlockingTest { repository.deleteAllData() } }
        verify { runBlockingTest { repository.insertDogList(dogListEntity) } }
    }

    @Test
    fun when_get_saved_dog_list_called_verify_call_api_method_from_repository() =
        runBlockingTest {
            viewModel.getSavedDogList()

            verify { runBlockingTest { repository.getSavedDogList() } }
        }

    @Test
    fun when_get_saved_dog_list_called_do_not_call_delete_method_from_repository() =
        runBlockingTest {
            viewModel.getSavedDogList()

            verify(inverse = true) { runBlockingTest { repository.deleteAllData() } }
        }

    @Test
    fun when_get_dog_list_called_do_not_call_get_saved_dog_list_method_from_repository() =
        runBlockingTest {
            viewModel.getDogList()

            verify(inverse = true) { runBlockingTest { repository.getSavedDogList() } }
        }
}