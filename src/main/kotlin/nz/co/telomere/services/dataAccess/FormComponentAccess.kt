package nz.co.telomere.services.dataAccess

import io.ktor.features.*
import nz.co.telomere.models.FormComponent
import org.sqlite.SQLiteDataSource
import java.sql.Connection

class FormComponentAccess {
    private val connection: Connection
    init {
        val sqlLiteDataSource = SQLiteDataSource()
        sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"
        this.connection = sqlLiteDataSource.connection
    }

    fun getAll(): List<FormComponent> {
        val query = "SELECT * FROM component"
        return Q(this.connection).query(query).into(FormComponent::class)
    }

    fun getOneByKey(key: String): FormComponent {
        val query = "SELECT * FROM component where key=?"
        val components = Q(this.connection).query(query, listOf(key)).into(FormComponent::class)
        if (components.isEmpty()) throw NotFoundException("FormComponent key ($key) not found")
        if (components.size > 1) throw IllegalStateException("Returned multiple components for key ($key)")
        return components.first()
    }

    /**
     * Current adds new formComponents and updates existing ones based on the id within the returned data
     * Likely a better way of doing this in the future
     */
    fun add(formComponent: FormComponent): FormComponent {
        return Q(this.connection).save(formComponent, "component")
    }

}