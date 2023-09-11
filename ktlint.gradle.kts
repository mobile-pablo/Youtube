val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.36.0")
}

tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args(
        "--android",
        "--reporter=html,output=${buildDir}/ktlint.html",
        "--reporter=plain",
        "--reporter=checkstyle",
        "src/**/*.kt"
    )
}

tasks.named("check") {
    dependsOn(ktlint)
}

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args(
        "--android",
        "-F",
        "src/**/*.kt"
    )
}
