package com.comment.application.data.repository

import com.comment.application.data.model.Comment
import com.comment.application.data.network.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.*

class CommentRepositoryTest {

    private lateinit var repository: CommentRepository
    private val apiService: ApiService = mock()

    @Before
    fun setup() {
        repository = CommentRepository(apiService)
    }

    @Test
    fun `getComments returns list of comments`() = runTest {
        // Arrange
        val mockComments = listOf(
            Comment(1, 101, "John", "john@example.com", "Test body")
        )
        whenever(apiService.getComments()).thenReturn(mockComments)

        // Act
        val result = repository.getComments()

        // Assert
        assertEquals(1, result.size)
        assertEquals("John", result[0].name)
        assertEquals("john@example.com", result[0].email)
    }

    @Test(expected = RuntimeException::class)
    fun `getComments throws exception when API fails`() = runTest {
        // Arrange
        whenever(apiService.getComments()).thenThrow(RuntimeException("API failure"))

        // Act
        repository.getComments() // Should throw
    }
}
