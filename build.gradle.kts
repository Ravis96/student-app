plugins {
    id("java")
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.github.node-gradle.node") version "3.2.1"
}

allprojects {
    group = "pl.ravis96.studentapp"
    version = "1.0-InDEV"

    apply(plugin = "java-library")

    repositories {
        mavenCentral()
        maven("https://repo.dreamcode.cc/releases")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.github.node-gradle.node")

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.28")
        annotationProcessor("org.projectlombok:lombok:1.18.28")
        testCompileOnly("org.projectlombok:lombok:1.18.28")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
    }
}