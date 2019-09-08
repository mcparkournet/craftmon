repositories {
	maven("https://repo.velocitypowered.com/releases")
}

dependencies {
	api(project(":craftmon-core"))
	compileOnly("com.velocitypowered:velocity-api:1.0.3")
}
