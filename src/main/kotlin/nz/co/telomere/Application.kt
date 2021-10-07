package nz.co.telomere

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import nz.co.telomere.plugins.*
import org.flywaydb.core.Flyway
import org.sqlite.SQLiteDataSource;

fun main() {
    migrateOnStart()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureSerialization()
    }.start(wait = true)
}

fun migrateOnStart() {
    val sqlLiteDataSource = SQLiteDataSource()
    sqlLiteDataSource.url = "jdbc:sqlite:./entity_builder.db"

    val flyway = Flyway.configure().dataSource(sqlLiteDataSource).locations("classpath:db/migration").load()
    flyway.migrate()
}