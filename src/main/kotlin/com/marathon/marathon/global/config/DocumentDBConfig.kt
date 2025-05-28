package com.marathon.marathon.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

@Configuration
class DocumentDBConfig : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName(): String {
        TODO("Not yet implemented")
    }
}