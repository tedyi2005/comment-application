package com.comment.application.ui.screens

import com.comment.application.data.model.Comment
import com.comment.application.data.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CommentViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CommentRepository
    private lateinit var viewModel: CommentViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `comments flow updates when repository returns data`() = runTest {
        // Given
        val mockComments = listOf(Comment(1, 1, "John", "john@example.com", "Nice post!"))
        whenever(repository.getComments()).thenReturn(mockComments)

        // When
        viewModel = CommentViewModel(repository)
        advanceUntilIdle() // Let the coroutine finish

        // Then
        val result = viewModel.comments.first()
        assertEquals(mockComments, result)
        verify(repository).getComments()
    }

    @Test
    fun `comments flow remains empty when repository throws exception`() = runTest {
        // Given
        whenever(repository.getComments()).thenThrow(RuntimeException("API failure"))

        // When
        viewModel = CommentViewModel(repository)
        advanceUntilIdle() // Let the coroutine finish

        // Then
        val result = viewModel.comments.first()
        assertEquals(emptyList<Comment>(), result)
        verify(repository).getComments()
    }
}
