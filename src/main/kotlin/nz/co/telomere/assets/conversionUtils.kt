package nz.co.telomere.assets

import kotlin.reflect.KClass

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

// https://stackoverflow.com/a/67452722
fun <T : Any> mapToObject(map: Map<String, Any?>, klass: KClass<T>): T {
    //Get default constructor
    val constructor =
        klass.constructors.minByOrNull { it.parameters.size } ?: throw Exception("Unable to find constructor")

    //Map constructor parameters to map values
    val args = constructor
        .parameters
        .map { it to map.get(it.name) }
        .toMap()

    //return object from constructor call
    return constructor.callBy(args)
}