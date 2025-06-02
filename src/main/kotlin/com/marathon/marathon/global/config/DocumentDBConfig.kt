package com.marathon.marathon.global.config

import com.marathon.marathon.global.mongo.MongodbProperties
import org.springframework.context.annotation.Configuration

@Configuration
class DocumentDBConfig(
    private val mongodbProperties: MongodbProperties
) {


}