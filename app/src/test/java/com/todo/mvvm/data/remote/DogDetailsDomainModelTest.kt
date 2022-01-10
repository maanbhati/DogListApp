package com.todo.mvvm.data.remote

import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DogDetailsDomainModelTest {
    private lateinit var domainModel: DogDetailsDomainModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        domainModel = DogDetailsDomainModel(IMAGE_URL, DOG_NAME)
    }

    @Test
    fun verify_domain_model_data() {
        assert(domainModel.dogName == DOG_NAME)
        assert(domainModel.dogImageUrl == IMAGE_URL)
    }

    companion object {
        const val DOG_NAME = "DogName"
        const val IMAGE_URL = "ImageUrl"
    }
}