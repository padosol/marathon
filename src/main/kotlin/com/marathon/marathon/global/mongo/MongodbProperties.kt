package com.marathon.marathon.global.mongo

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MongodbProperties{

    @Value("\${}")
    lateinit var database: String
}