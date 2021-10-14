package nz.co.telomere.services.dataAccess

import nz.co.telomere.assets.*
import org.sqlite.SQLiteException
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*
import java.sql.Types
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

/**
 * Usage:
 * QueryToClass(connection).query("SELECT * FROM components WHERE key=?", listOf("short-text")).into<Component>()
 */
class Q(val conn: Connection) {
    lateinit var statement: PreparedStatement

    fun <T: Any >save(entity: T, tableName: String? = null): T {
        val klass: KClass<T> = entity::class as KClass<T>
        val table = tableName ?: entity::class.simpleName
        val id = readInstanceProperty<Int>(entity, "id")
        val props = klass.declaredMemberProperties.filter { it.name != "id"}
        val columns = props.map { it.name.camelToSnakeCase() }

        val query = if (id == null) {
            "insert into $table (${columns.joinToString(", ")}) values (${"?,".repeat(columns.size).dropLast(1)})"
        } else {
            "update $table set ${columns.joinToString("=?,")}=? where id=?"
        }
        val saveStatement = this.conn.prepareStatement(query)

        for ((index, prop) in props.withIndex()) {
            when (prop.returnType.classifier) {
                Int::class -> setValue(saveStatement, readInstanceProperty<Int>(entity, prop.name), index + 1, Types.INTEGER)
                Double::class -> setValue(saveStatement, readInstanceProperty<Double>(entity, prop.name), index + 1, Types.DOUBLE)
                Boolean::class -> setValue(saveStatement, readInstanceProperty<Boolean>(entity, prop.name), index + 1, Types.BOOLEAN)
                String::class -> setValue(saveStatement, readInstanceProperty<String>(entity, prop.name), index + 1, Types.NVARCHAR)
                else -> throw Exception("Property returnType not recognised: ${prop.returnType.classifier.toString()}")
            }
        }

        if (id != null) {
            // Update query, so add on the where clause
            saveStatement.setInt(props.size + 1, id)
        }

        try {
            saveStatement.execute()
        } catch (e: SQLiteException) {
            throw Exception(e.toString())
        }
        return entity
    }

    fun query(rawQuery: String?, params: List<Any> = listOf()): Q {
        this.statement = this.conn.prepareStatement(rawQuery)
        for ((index, parameter) in params.withIndex()) {
            when (parameter) {
                is Int -> this.statement.setInt(index + 1, parameter)
                is Double -> this.statement.setDouble(index + 1, parameter)
                is Boolean -> this.statement.setBoolean(index + 1, parameter)
                is String -> this.statement.setString(index + 1, parameter)
                else -> this.statement.setString(index + 1, parameter.toString())
            }
        }
        return this
    }

    fun <T: Any>into(clazz: KClass<T>): List <T> {
        // TODO: Think about how we hydrate into relationships, especially without creating circular references into
        //  one-to-many/many-to-one reflected relationships
        val results: MutableList<T> = mutableListOf()
        val isSuccess = this.statement.execute()
        if (!isSuccess) throw Exception("Query failed")

        val rs = this.statement.resultSet
        val args: MutableMap<String, Any?> = mutableMapOf()
        while (rs.next()) {
            for (index in 1..rs.metaData.columnCount) {
                val colName = rs.metaData.getColumnName(index)
                    args[colName.snakeToCamelCase()] = when (rs.metaData.getColumnType(index)) {
                        Types.INTEGER -> rs.getInt(index)
                        Types.DOUBLE -> rs.getDouble(index)
                        Types.BOOLEAN -> rs.getBoolean(index)
                        Types.NVARCHAR, Types.VARCHAR -> rs.getString(index)
                        else -> throw Exception("Data type not recognised ${rs.metaData.getColumnType(index)}")
                    }
            }

            results.add(mapToObject(args, clazz))
        }

        return Collections.unmodifiableList(results)
    }


}

