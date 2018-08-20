package io.henry.WebfluxAndSwagger.webflux.react

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
@ComponentScan("io.henry.WebfluxAndSwagger")
class Routing {
    @Bean
    fun booksRouter(handler: BooksHandler) = router {
        ("/books").nest {
            GET("/", handler::getAll)
            GET("/{title}", handler::getBook)
            POST("/", handler::addBook)
        }
    }
}