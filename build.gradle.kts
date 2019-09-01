import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.1.7.RELEASE"
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.50"

    // Apply the application plugin to add support for building a CLI application.
    application
}

val kotlinVersion = "1.3.50"
val springBootVersion = "2.1.7.RELEASE"

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-script-util:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:$kotlinVersion")
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")


    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application
    mainClassName = "idv.freddie.demo.ApplicationKt"
}

tasks.getByName<BootJar>("bootJar") {
    requiresUnpack("**/kotlin*.jar")
}
