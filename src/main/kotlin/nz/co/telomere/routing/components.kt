package nz.co.telomere.routing

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import nz.co.telomere.dataAccess.Q
import nz.co.telomere.models.FormComponent
import org.sqlite.SQLiteDataSource

fun Routing.componentApi() {
    route("/api/component") {
        get("/") {
            call.respond(mapOf("success" to true))
        }

        get("/{key}") {
            val sqlLiteDataSource = SQLiteDataSource()
            sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"
            val key = call.parameters["key"] ?: return@get

            val query = "SELECT * FROM component where key=?"
            val components = Q(sqlLiteDataSource.connection).query(query, listOf(key)).into<FormComponent>()
            call.respond(components)
        }
    }
}