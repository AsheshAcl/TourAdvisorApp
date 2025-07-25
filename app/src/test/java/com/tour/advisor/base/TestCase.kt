package com.tour.advisor.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalCoroutinesApi::class)
open class TestCase {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setupCoroutine() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun afterFinished() {
        Dispatchers.resetMain()
    }
}