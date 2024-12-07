plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "1.9.25"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

group = "SnapIt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.kwhat:jnativehook:2.2.2")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks {
    shadowJar {
        manifest {
            attributes["Main-Class"] = "SnapIt.MainKt"
        }

        from("src/main/resources") {
            include("**/*.properties")
        }
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
