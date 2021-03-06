repositories {
	maven("https://papermc.io/repo/repository/maven-public") {
		content {
			includeGroup("com.destroystokyo.paper")
			includeGroup("net.md-5")
		}
	}
}

dependencies {
	api(project(":craftmon-core"))
	compileOnly("com.destroystokyo.paper:paper-api:1.15.2-R0.1-SNAPSHOT")
}
