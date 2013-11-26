organization := "com.gu"

scalaVersion in ThisBuild := "2.10.2"

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature", "-language:postfixOps")

resolvers += "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases" 
