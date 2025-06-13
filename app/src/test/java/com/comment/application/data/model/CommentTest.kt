package com.comment.application.data.model

import org.junit.Assert.*
import org.junit.Test

class CommentTest {

    @Test
    fun `test Comment data class equality`() {
        val comment1 = Comment(1, 101, "John", "john@example.com", "Nice post!")
        val comment2 = Comment(1, 101, "John", "john@example.com", "Nice post!")

        assertEquals(comment1, comment2)
    }

    @Test
    fun `test Comment copy function`() {
        val original = Comment(1, 101, "John", "john@example.com", "Nice post!")
        val copied = original.copy(body = "Updated comment")

        assertEquals("Updated comment", copied.body)
        assertEquals(original.id, copied.id)
        assertNotEquals(original.body, copied.body)
    }
}
