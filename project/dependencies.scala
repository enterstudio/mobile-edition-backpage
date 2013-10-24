import sbt._

object Dependencies {

  val clapper = "org.clapper" %% "grizzled-slf4j" % "1.0.1"

  val contentApi = "com.gu.openplatform" %% "content-api-client" % "2.7"

  // http://code.google.com/p/reflections/issues/detail?id=140
  val guardianManagementPlay = "com.gu" %% "management-play" % "5.26" exclude("javassist", "javassist")

  val guardianConfiguration = "com.gu" %% "configuration" % "3.9"
}
