plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.7.10"
}

group = "ru.kubsu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation(kotlin("stdlib"))
    implementation("com.charleskorn.kaml:kaml:0.52.0")
    implementation("org.postgresql:postgresql:42.7.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}