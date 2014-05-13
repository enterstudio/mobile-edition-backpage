organization := "com.gu"

scalaVersion in ThisBuild := "2.10.3"

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature", "-language:postfixOps")

resolvers in ThisBuild += "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases" 
