plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

javafx {
    version = "21"
    modules("javafx.controls", "javafx.fxml")
}
repositories {
    mavenCentral()
}


application {
    mainClass.set("core.Main")
}
