import sbt._

object Plugins extends Build {
  val playArtifactPluginVersion = "v2.8"

  lazy val plugins = Project("main", file(""))
    .dependsOn(uri("git://github.com/guardian/sbt-version-info-plugin.git#v2.7"))
    .dependsOn(uri("git://github.com/guardian/sbt-play-artifact.git#" + playArtifactPluginVersion))
}
