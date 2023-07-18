import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "5.0.0"
}

node {
    version.set("20.4.0")
    download.set(true)
}

val buildTask = tasks.register<NpmTask>("buildReact") {
    args.set(listOf("run", "build"))
    dependsOn(tasks.npmInstall)

    inputs.dir(project.fileTree("src"))
    inputs.dir("node_modules")
    inputs.files("tsconfig.json")

    outputs.dir(file("${project.buildDir}/frontend"))
}

tasks.withType<JavaCompile> {
    dependsOn(buildTask)
}