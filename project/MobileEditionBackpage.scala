import sbt._
import Keys._
import play.Project._
import Dependencies._
 
object ApplicationBuild extends Build {
 
  val appName         = "mobile-edition-backpage"
  val appVersion      = "1.0-SNAPSHOT"

  resolvers += "Guardian GitHub Releases" at "http://guardian.github.com/maven/repo-releases"
 
  val appDependencies = Nil
 
  val main = play.Project(appName, appVersion, appDependencies) 
    .settings(
      testOptions in Test := Nil,
      libraryDependencies ++= Seq(
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

