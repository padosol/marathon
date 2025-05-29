package com.marathon.marathon.global.config

import com.marathon.marathon.global.mongo.MongodbProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

@Configuration
class DocumentDBConfig(
    private val mongodbProperties: MongodbProperties
) : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {

        TODO("Not yet implemented")
    }


}