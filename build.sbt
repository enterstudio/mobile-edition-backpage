name := "mobile-edition-backpage"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Guardian GitHub Releases" at "http://guardian.github.com/maven/repo-releases"
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "org.clapper" %% "grizzled-slf4j" % "1.0.1",
  "com.gu.openplatform" %% "content-api-client" % "2.7",
  "com.gu" %% "management-play" % "6.0" exclude("javassist", "javassist")
)     

play.Project.playScalaSettings
