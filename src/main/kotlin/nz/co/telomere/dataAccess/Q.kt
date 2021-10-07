package nz.co.telomere.dataAccess

import nz.co.telomere.assets.mapToObject
import nz.co.telomere.assets.snakeToCamelCase
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*
import java.sql.Types

/**
 * Usage:
 * QueryToClass(connection).query("SELECT * FROM components WHERE key=?", listOf("short-text")).into<Component>()
 */
class Q(val conn: Connection) {
    lateinit var statement: PreparedStatement

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

    inline fun <reified T: Any>into(): List <T> {
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

            results.add(mapToObject(args, T::class))
        }

        return Collections.unmodifiableList(results)
    }


}

