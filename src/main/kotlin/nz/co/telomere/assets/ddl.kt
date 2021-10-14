package nz.co.telomere.assets

import nz.co.telomere.models.Entity

val dataTypeMapToPG = mapOf<String, String>(
    "text" to "text",
    "integer" to "integer",
    "boolean" to "boolean",
    "double" to "numeric",
)

/**
 * Creates a Postgres table per entity
 */
fun createTable(entity: Entity, schema: String = "public"): String {
    // Guard clause - don't process if no properties
    if (entity.properties === null || entity.properties.isEmpty()) return ""

    var tableDefinition = "CREATE TABLE $schema.\"${entity.key}\" (\n"
    val columnDefs: List<String> = entity.properties?.map {
        val dataType = dataTypeMapToPG[it.dataType]
        val defaultValue = if (it.defaultValue != null) " DEFAULT '${it.defaultValue}'::$dataType" else ""
        val neverNull = if (it.neverNull) " NOT NULL" else ""

        "${it.key.camelToSnakeCase()} $dataType$defaultValue$neverNull"
    } ?: listOf()

    val relationships: List<String> = if (!entity.relationships.isNullOrEmpty()) {
        entity.relationships.map {
            // TODO: Never null?
            val name = it.name?.camelToSnakeCase() ?: it.entity.camelToSnakeCase() + "_id"
            "${name} string REFERENCES ${it.entity.camelToSnakeCase()} (id)"
        }
    } else listOf()
    tableDefinition += columnDefs.joinToString(separator=",\n")
    tableDefinition += if (!relationships.isNullOrEmpty()) ",\n" + relationships.joinToString(separator=",\n") else ""
    tableDefinition += ");"

    // Add indexes
    if (!entity.relationships.isNullOrEmpty()) {
        entity.relationships.forEach {
            val name = it.name?.camelToSnakeCase() ?: it.entity.camelToSnakeCase() + "_id"
            tableDefinition += "\nCREATE INDEX ${entity.key}_${name}_idx ON ${entity.key} ($name);"
        }
    }
    return tableDefinition
}
