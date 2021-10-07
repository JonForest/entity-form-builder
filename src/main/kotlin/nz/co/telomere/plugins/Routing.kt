package nz.co.telomere.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import nz.co.telomere.assets.createClass
import nz.co.telomere.assets.createTable
import nz.co.telomere.models.Configuration

//import io.ktor.request.*

fun Application.configureRouting() {


    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/field-types") {
            call.respond(listOf(12, 12, 12))
        }

        post("/preview") {
            val config = call.receive<Configuration>()
//            val config = Json.decodeFromString<Configuration>("""{"entities":[{"name":"test","description":"this is a test"}]}""")
//            call.respondText(config.entities.size.toString())
//            call.respondText(Json.encodeToString(Configuration(entities=listOf(Entity(name="test", description="this is a test")))))
//            val entity = Json.decodeFromString<Entity>(call.receiveText())
//            val entity = call.receive<Entity>();
            val ddl = config.entities.map { createTable(it)}.joinToString(separator = "\n\n")
            val dataClasses = config.entities.map { createClass(it) }.joinToString(separator = "\n\n")
            call.respondText(ddl + "\n\n" + dataClasses)
        }

        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
