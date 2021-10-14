import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import nz.co.telomere.assets.createClass
import nz.co.telomere.assets.createTable
import nz.co.telomere.models.Configuration

fun Routing.entityBuilderApis() {
    route("/api") {
        get("") {
            call.respondText("Hello World!")
        }

        get("/field-types") {
            call.respond(listOf(12, 12, 12))
        }

        post("/preview/ddl") {
            val config = call.receive<Configuration>()
            val ddl = config.entities.map { createTable(it) }.joinToString(separator = "\n\n")
            call.respondText(ddl)
        }

        post("/preview/dataclass") {
            val config = call.receive<Configuration>()
            val dataClasses = config.entities.map { createClass(it) }.joinToString(separator = "\n\n")
            call.respondText(dataClasses)
        }
    }
}