plugins {
    kotlin("jvm") version "1.3.72"
    application
}

repositories {
    jcenter()
    mavenCentral() // JCenter does not have all AWS CDK (1.20.0) dependencies
    maven("https://jitpack.io")
    maven("https://dl.bintray.com/justincase/aws-cdk-kotlin-dsl")
}

application {
    mainClassName = "com.github.youta1119.cdk.examples.Main"
}

afterEvaluate {
    tasks.findByName("run")?.apply {
        outputs.dir("cdk.out")
    }
}

val awsCdkDslVersion = "1.51.0-0.6.6"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "amplify", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "events", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "events-targets", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "lambda", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "lambda-event-sources", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "sns", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "sns-subscriptions", awsCdkDslVersion)
    implementation("jp.justincase.aws-cdk-kotlin-dsl", "secretsmanager", awsCdkDslVersion)
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "6.1"
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}