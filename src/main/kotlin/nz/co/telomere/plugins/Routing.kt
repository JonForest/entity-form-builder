package nz.co.telomere.plugins

import entityBuilderApis
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import nz.co.telomere.assets.createClass
import nz.co.telomere.assets.createTable
import nz.co.telomere.models.Configuration
import nz.co.telomere.routing.componentApi

//import io.ktor.request.*

fun Application.configureRouting() {


    routing {

        entityBuilderApis()
        componentApi()

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
