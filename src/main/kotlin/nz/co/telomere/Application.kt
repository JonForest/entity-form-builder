package nz.co.telomere

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import nz.co.telomere.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureSerialization()
    }.start(wait = true)
}
