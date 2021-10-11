package nz.co.telomere.routing

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import nz.co.telomere.models.FormComponent
import nz.co.telomere.services.dataAccess.FormComponentAccess

fun Routing.componentApi() {
    route("/api/component") {
        get("") {
            call.respond(FormComponentAccess().getAll())
        }

        post("") {
            val formComponent = call.receive<FormComponent>()
            call.respond(FormComponentAccess().add(formComponent))
        }

        get("/{key}") {
            val key = call.parameters["key"] ?: return@get
            call.respond(FormComponentAccess().getOneByKey(key))
        }

        post("/{key}") {
            val formComponent = call.receive<FormComponent>()
            call.respond(FormComponentAccess().add(formComponent))
        }


    }
}