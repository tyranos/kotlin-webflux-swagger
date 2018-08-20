package io.henry.WebfluxAndSwagger

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@ComponentScan("io.henry")
@EnableReactiveMongoRepositories(basePackageClasses = [MongoConfig::class])
@EnableMongoAuditing
class MongoConfig : AbstractReactiveMongoConfiguration() {

    @Value("\${mongo.uri}")
    lateinit var mongoUri: String

    override fun reactiveMongoClient(): MongoClient = MongoClients.create(mongoUri)

    override fun getDatabaseName(): String = "test"

}