package com.example.kotlin_spring_project.dto

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(true, data, null)
        }

        fun <T> failure(message: String): ApiResponse<T> {
            return ApiResponse(false, null, message)
        }
    }
}
