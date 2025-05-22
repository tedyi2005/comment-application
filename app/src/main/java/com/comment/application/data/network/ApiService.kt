package com.comment.application.data.network

import com.comment.application.data.model.Comment
import retrofit2.http.GET

interface ApiService {
    @GET("comments")
    suspend fun getComments(): List<Comment>
}
