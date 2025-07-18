package com.example.kotlin_spring_project.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager

@Configuration
class CustomAuthManagerConfig(
    private val customAuthenticationProvider: CustomAuthenticationProvider
) {

    @Bean
    fun customAuthenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(customAuthenticationProvider))
    }
}
