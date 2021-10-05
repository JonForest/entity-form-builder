package nz.co.telomere.assets

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
        it.value.replace("_","")
            .uppercase()
    }
}

//fun String.snakeToUpperCamelCase(): String {
//    return this.snakeToLowerCamelCase().replaceFirstChar(Char::uppercase)
//}