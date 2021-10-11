package nz.co.telomere.assets

import java.sql.PreparedStatement
import java.sql.Statement
import java.sql.Types
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty1

val snakeRegex = "_[a-zA-Z]".toRegex()
val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

// String extensions
fun String.camelToSnakeCase(): String {
    return camelRegex.replace(this) {
        "_${it.value}"
    }.lowercase()
}

fun String.snakeToCamelCase(): String {
    return snakeRegex.replace(this) {
        it.value.replace("_", "")
            .uppercase()
    }
}

//fun String.snakeToUpperCamelCase(): String {
//    return this.snakeToLowerCamelCase().replaceFirstChar(Char::uppercase)
//}

fun <T: Any>getConstructor(klass: KClass<T>): KFunction<T> {
   return klass.constructors.minByOrNull { it.parameters.size } ?: throw Exception("Unable to find constructor")
}

// https://stackoverflow.com/a/67452722
fun <T : Any> mapToObject(map: Map<String, Any?>, klass: KClass<T>): T {
    //Get default constructor
    val constructor = getConstructor(klass)


    //Map constructor parameters to map values
    val args = constructor
        .parameters
        .map { it to map.get(it.name) }
        .toMap()

    //return object from constructor call
    return constructor.callBy(args)
}

//fun <T : Any> constructorProps(klass: KClass<T>): List<String?> {
//    //Get default constructor
//    val constructor = getConstructor(klass)
//
//    //Map constructor parameters to map values
//    return constructor
//        .parameters
//        .map { it.name }
//}

@Suppress("UNCHECKED_CAST")
fun <R> readInstanceProperty(instance: Any, propertyName: String): R? {
    val property = instance::class.members
        // don't cast here to <Any, R>, it would succeed silently
        .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(instance) as R
}

fun setValue(statement: PreparedStatement, value: Any?, index: Int, sqlType: Int) {
    if (value == null) {
        statement.setNull(index, sqlType)
    } else {
        when (sqlType) {
            Types.INTEGER -> statement.setInt(index, value as Int)
            Types.DOUBLE -> statement.setDouble(index, value as Double)
            Types.BOOLEAN -> statement.setBoolean(index, value as Boolean)
            Types.NVARCHAR, Types.VARCHAR -> statement.setString(index, value as String)
            else -> statement.setString(index, value.toString())
        }
    }
}