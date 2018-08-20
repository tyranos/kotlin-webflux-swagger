package io.henry.WebfluxAndSwagger.webflux.react

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.time.Duration

@Document
data class Book(@Id val id: String? = null, val title: String, val author: String)

@Repository
interface BookRepository: ReactiveCrudRepository<Book, String> {
    fun findByTitle(name: String): Mono<Book>
}

@Component
class BooksHandler(private var repository: BookRepository) {
    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        val interval = Flux.interval(Duration.ofSeconds(1))

        val books = repository.findAll()
        return ok().bodyToServerSentEvents(Flux.zip(interval, books).map({it.t2}))
    }

    fun getBook(request: ServerRequest): Mono<ServerResponse> {
        val title = request.pathVariable("title")

        return ok().syncBody(repository.findByTitle(title))
    }

    fun addBook(request: ServerRequest): Mono<ServerResponse> {
        val book = request.bodyToMono<Book>()

        return ok().syncBody(repository.saveAll(book).toMono())
    }
}



