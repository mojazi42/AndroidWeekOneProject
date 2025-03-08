package com.example.weekoneproject

data class User(val name: String, val age: Int, val email: String)

val users = listOf(
    User("Alice", 22, "alice@example.com"),
    User("Bob", 17, "bob@example.com"),
    User("Charlie", 25, "charlie@example.com")
)
val adults = users.filter { it.age >= 18 }
