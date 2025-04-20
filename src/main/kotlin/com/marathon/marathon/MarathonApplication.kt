package com.marathon.marathon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarathonApplication

fun main(args: Array<String>) {
    runApplication<MarathonApplication>(*args)
}
