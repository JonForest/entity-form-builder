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

    var tableDefinition = "CREATE TABLE $schema.\"${entity.name}\" (\n"
    val columnDefs: List<String> = entity.properties?.map {
        val dataType = dataTypeMapToPG[it.dataType]
        val defaultValue = if (it.defaultValue != null) " DEFAULT '${it.defaultValue}'::$dataType" else ""
        val neverNull = if (it.neverNull) " NOT NULL" else ""

        "${it.name.camelToSnakeCase()} $dataType$defaultValue$neverNull"
    } ?: listOf()
    tableDefinition += columnDefs.joinToString(separator=",\n")
    tableDefinition += ");"
    return tableDefinition
}
