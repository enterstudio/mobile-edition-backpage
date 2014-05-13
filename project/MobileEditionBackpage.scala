import sbt._
import Keys._
import com.gu.deploy.MagentaArtifact._
import sbtassembly.Plugin._
import AssemblyKeys._
import play.Project._
import Dependencies._

object ApplicationBuild extends Build {
  val appName         = "mobile-edition-backpage"
  val appVersion      = "1.0-SNAPSHOT"

  resolvers += "Guardian GitHub Releases" at "http://guardian.github.com/maven/repo-releases"
 
  val appDependencies = Nil
 
  val main = play.Project(appName, appVersion, appDependencies) 
    .settings(magentaArtifactSettings: _*)
    .settings(
        mergeStrategy in assembly <<= (mergeStrategy in assembly) {
          (old) => {
            case PathList("gu-conf", xs@_*) => MergeStrategy.filterDistinctLines
            case PathList("scalax", xs@_*) => MergeStrategy.first
            case PathList("play", xs@_*) => MergeStrategy.first
            case PathList("org", "apache", "commons", "logging", xs@_*) => MergeStrategy.first
            case x => old(x)
          }
        }
    )
    .settings(
      libraryDependencies ++= Seq(
        scalaIo % "test",
        scalaUri,
        clapper,
        contentApi,
        guardianManagementPlay,
        guardianConfiguration
      ),
      ivyXML :=
        <dependencies>
          <exclude module="log4j"/>
          <exclude module="slf4j-log4j12"/>
        </dependencies>
    )
}
