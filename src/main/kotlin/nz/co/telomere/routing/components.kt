package nz.co.telomere.routing

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import nz.co.telomere.dataAccess.Q
import nz.co.telomere.models.Configuration
import nz.co.telomere.models.FormComponent
import nz.co.telomere.models.ResponseError
import org.sqlite.SQLiteDataSource
import java.awt.Component

fun Routing.componentApi() {
    route("/api/component") {
        get("") {
            val sqlLiteDataSource = SQLiteDataSource()
            sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"

            val query = "SELECT * FROM component"
            val components = Q(sqlLiteDataSource.connection).query(query).into<FormComponent>()
            call.respond(components)
        }

        post("") {
            val formComponent = call.receive<FormComponent>()
            val sqlLiteDataSource = SQLiteDataSource()
            sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"
            val savedComp: FormComponent
            try {
                savedComp = Q(sqlLiteDataSource.connection).save(formComponent, "component")
            } catch (e: Exception) {
                // TODO: Move the error handling to the plugins
                val errorResponse = ResponseError(error = e.toString())
                call.respond(errorResponse)
                return@post
            }
            call.respond(savedComp)
        }

        get("/{key}") {
            val sqlLiteDataSource = SQLiteDataSource()
            sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"
            val key = call.parameters["key"] ?: return@get

            val query = "SELECT * FROM component where key=?"
            val components = Q(sqlLiteDataSource.connection).query(query, listOf(key)).into<FormComponent>()
            call.respond(components.first()) // Should only need to return the first value
        }

        post("/{key}") {
            val formComponent = call.receive<FormComponent>()
            val sqlLiteDataSource = SQLiteDataSource()
            sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"
            val savedComp: FormComponent
            try {
                savedComp = Q(sqlLiteDataSource.connection).save(formComponent, "component")
            } catch (e: Exception) {
                // TODO: Move the error handling to the plugins
                val errorResponse = ResponseError(error = e.toString())
                call.respond(errorResponse)
                return@post
            }
            call.respond(savedComp)
        }


    }
}