repositories {
	maven("https://papermc.io/repo/repository/maven-public")
}

dependencies {
	api(project(":craftmon-core"))
	compileOnly("io.github.waterfallmc:waterfall-api:1.14-SNAPSHOT")
}
