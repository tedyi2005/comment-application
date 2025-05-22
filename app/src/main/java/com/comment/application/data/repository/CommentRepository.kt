package com.comment.application.data.repository

import com.comment.application.data.model.Comment
import com.comment.application.data.network.ApiService
import javax.inject.Inject

class CommentRepository @Inject constructor(private val api: ApiService) {
    suspend fun getComments(): List<Comment> = api.getComments()
}
