package ru.crub.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinCrudAppApplication

fun main(args: Array<String>) {
    runApplication<KotlinCrudAppApplication>(*args)
}
