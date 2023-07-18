import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "5.0.0"
}

node {
    version.set("20.4.0")
    npmVersion.set("9.8.0")

    download.set(true)

    workDir.set(file("${project.buildDir}/nodejs"))
    npmWorkDir.set(file("${project.buildDir}/npm"))
}

tasks.register<NpmTask>("appNpmInstall") {
    workingDir.set(file("${project.projectDir}/src/main/webapp"))

    args.set(listOf("install"))
}

tasks.register<NpmTask>("appNpmBuild") {
    dependsOn("appNpmInstall")

    workingDir.set(file("${project.projectDir}/src/main/webapp"))
    args.set(listOf("run", "build"))
}

tasks.register<Copy>("copyWebApp") {
    dependsOn("appNpmBuild")

    from("src/main/webapp/build")
    into("build/resources/main/static/.")
}

tasks.withType<JavaCompile> {
    dependsOn("copyWebApp")
}