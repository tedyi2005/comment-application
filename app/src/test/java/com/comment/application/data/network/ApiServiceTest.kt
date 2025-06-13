package com.comment.application.data.network

import com.comment.application.data.model.Comment
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))  // use mock server URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getComments should return list of Comment`() = runBlocking {
        val mockJson = """
            [
                {
                    "postId": 1,
                    "id": 1,
                    "name": "Test User",
                    "email": "test@example.com",
                    "body": "This is a test comment"
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockJson)
        )

        val response: List<Comment> = apiService.getComments()

        assertThat(response).isNotEmpty()
        assertThat(response[0].id).isEqualTo(1)
        assertThat(response[0].name).isEqualTo("Test User")
    }
}
