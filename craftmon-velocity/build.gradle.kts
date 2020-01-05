repositories {
	maven("https://repo.velocitypowered.com/releases") {
		content {
			includeGroup("com.velocitypowered")
		}
	}
}

dependencies {
	api(project(":craftmon-core"))
	compileOnly("com.velocitypowered:velocity-api:1.0.4")
}
