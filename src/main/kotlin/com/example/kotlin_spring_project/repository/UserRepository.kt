package com.example.kotlin_spring_project.repository

import com.example.kotlin_spring_project.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun existsByEmail(email: String): Boolean

    override fun findAll(): List<User>
}
