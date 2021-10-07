val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    id("org.flywaydb.flyway") version "8.0.0"
}

group = "nz.co.telomere"
version = "0.0.1"
application {
    mainClass.set("nz.co.telomere.ApplicationKt")
}

repositories {
    mavenCentral()
}

flyway {
    url="jdbc:sqlite:./entity-form-builder/entity_builder.db"
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.flywaydb:flyway-core:8.0.0")
    implementation("org.xerial:sqlite-jdbc:3.30.1")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}