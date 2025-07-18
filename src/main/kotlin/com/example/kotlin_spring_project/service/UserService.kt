package com.example.kotlin_spring_project.service

import com.example.kotlin_spring_project.model.User
import com.example.kotlin_spring_project.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    // Find user by email
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email).orElse(null) // orElse(null) to return null instead of Optional
    }

    fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }
}
