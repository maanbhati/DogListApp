package com.todo.mvvm.data.remote

import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DogListDomainModelTest {
    private lateinit var domainModel: DogListDomainModel
    private lateinit var dogList: List<String>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        dogList = listOf(TEST1, TEST2)
        domainModel = DogListDomainModel(dogList)
    }

    @Test
    fun verify_domain_model_data() {
        assert(domainModel.dogList == dogList)
        assert(domainModel.dogList[0] == TEST1)
        assert(domainModel.dogList[1] == TEST2)
    }

    companion object {
        const val TEST1 = "Test1"
        const val TEST2 = "Test2"
    }
}