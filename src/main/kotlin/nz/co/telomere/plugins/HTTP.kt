package nz.co.telomere.plugins

import io.ktor.features.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import nz.co.telomere.models.ResponseError
import org.sqlite.SQLiteException

fun Application.configureHTTP() {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
    install(CORS) {
        method(HttpMethod.Options)
        header(HttpHeaders.ContentType)
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(StatusPages) {
        exception<Throwable> { cause -> call.respond(ResponseError(error = cause.toString())) }
    }
}
