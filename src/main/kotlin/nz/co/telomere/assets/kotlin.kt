package nz.co.telomere.assets

import nz.co.telomere.assets.camelToSnakeCase
import nz.co.telomere.models.Entity



val dataTypeMapToClass = mapOf<String, String>(
    "text" to "String",
    "integer" to "Int",
    "boolean" to "Boolean",
    "double" to "Double",
)

/**
 * Creates a Postgres table per entity
 */
fun createClass(entity: Entity): String {
    // Guard clause - don't process if no properties
    if (entity.properties === null || entity.properties.isEmpty()) return ""

    var classDefinition = "data class ${entity.name.replaceFirstChar(Char::uppercase)} (\n"
    val propertyDefs: List<String> = entity.properties?.map {
        val isNullable = !it.neverNull
        // If the data type can be null, then mark it as optional
        val dataType = dataTypeMapToClass[it.dataType] + if (isNullable) "?" else ""
        // If we have a default value, then set it after the data type
        val defaultValue = if (it.defaultValue != null) when (it.dataType) {
           "text" -> " = \"${it.defaultValue}\""
           else -> " = ${it.defaultValue}"

        // If the datatype has been set to be nullable, and no other default value provided, ensure the default val
        // is null
        } else if (isNullable) " = null" else ""

        // TODO: Using `val` as the safer default, but will need to use `var` if we want to programmatically edit
        //       the values
        "val ${it.name}: $dataType$defaultValue"
    } ?: listOf()
    classDefinition += propertyDefs.joinToString(separator=",\n")
    classDefinition += ");"
    return classDefinition
}
