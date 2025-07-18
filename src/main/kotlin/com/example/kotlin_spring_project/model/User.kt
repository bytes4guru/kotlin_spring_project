package com.example.kotlin_spring_project.model

import jakarta.persistence.*
import java.util.Objects

@Entity
@Table(name = "users")  // The table name in the database
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    val id: Long? = null,

    @Column(name = "Password", nullable = false)  // Store hashed password
    var password: String,

    @Column(name = "LoginID", nullable = false, unique = true)  // Unique email field
    var email: String,

    @Column(name = "FirstName")
    var firstName: String? = null,

    @Column(name = "LastName")
    var lastName: String? = null
) {
    // Override equals and hashCode for proper comparison and uniqueness
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id && email == other.email
    }

    override fun hashCode(): Int {
        return Objects.hash(id, email)
    }

    // To String Method for easy debugging
    override fun toString(): String {
        return "User(id=$id, email='$email', firstName=$firstName, lastName=$lastName)"
    }
}
