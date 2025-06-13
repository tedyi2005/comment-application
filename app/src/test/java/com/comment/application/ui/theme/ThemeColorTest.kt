package com.comment.application.ui.theme

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class ThemeColorTest {

    @Test
    fun `check Purple80 color value`() {
        assertEquals(Color(0xFFD0BCFF), Purple80)
    }

    @Test
    fun `check Pink40 color value`() {
        assertEquals(Color(0xFF7D5260), Pink40)
    }
}
