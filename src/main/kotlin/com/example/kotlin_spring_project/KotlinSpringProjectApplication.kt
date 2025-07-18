package com.example.kotlin_spring_project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.Dotenv

@SpringBootApplication
class KotlinSpringProjectApplication

fun main(args: Array<String>) {
	val dotenv = Dotenv.configure().load()
	System.setProperty("DB_URL", dotenv.get("DB_URL"));
	System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
	System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	runApplication<KotlinSpringProjectApplication>(*args)
}
