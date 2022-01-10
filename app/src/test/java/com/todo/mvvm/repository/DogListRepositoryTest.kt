package com.todo.mvvm.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.todo.mvvm.data.local.DogListDao
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.networkmodule.api.Api
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogListRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var api: Api

    @MockK
    private lateinit var dogListDao: DogListDao

    @MockK
    private lateinit var dogListEntity: DogListEntity

    private lateinit var repository: DogListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repository = DogListRepository(api, dogListDao)
    }

    @Test
    fun when_get_dog_list_method_called_verify_method_call_from_api() = runBlockingTest {
        repository.getDogList()

        verify { runBlockingTest { api.getDogList() } }
    }

    @Test
    fun when_get_dog_image_method_called_verify_method_call_from_api() = runBlockingTest {
        val dogName = "Name"
        repository.getDogImage(dogName)

        verify { runBlockingTest { api.getDogImage(dogName) } }
    }

    @Test
    fun when_get_saved_dog_list_method_called_verify_method_call_from_dao() = runBlockingTest {
        repository.getSavedDogList()

        verify { runBlockingTest { dogListDao.getSavedDogList() } }
    }

    @Test
    fun when_insert_dog_list_entity_method_called_verify_method_call_from_dao() = runBlockingTest {
        repository.insertDogList(dogListEntity)

        verify { runBlockingTest { dogListDao.insert(dogListEntity) } }
    }

    @Test
    fun when_delete_all_data_method_called_verify_method_call_from_dao() = runBlockingTest {
        repository.deleteAllData()

        verify { runBlockingTest { dogListDao.deleteAll() } }
    }

    @Test
    fun when_insert_dog_list_entity_method_called_do_not_call_delete_method_from_dao() =
        runBlockingTest {
            repository.insertDogList(dogListEntity)

            verify(inverse = true) { runBlockingTest { dogListDao.deleteAll() } }
        }

    @Test
    fun when_get_dog_list_method_called_do_not_call_get_saved_dog_list_from_dao() =
        runBlockingTest {
            repository.getDogList()

            verify(inverse = true) { runBlockingTest { dogListDao.getSavedDogList() } }
        }
}