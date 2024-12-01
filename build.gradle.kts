plugins {
    kotlin("jvm") version "2.0.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
        resources.srcDir("resources")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }
}
