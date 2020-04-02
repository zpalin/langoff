name := "scala2"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies +=  "software.amazon.awssdk" % "sdk-core" % "2.11.6"
libraryDependencies +=  "software.amazon.awssdk" % "s3" % "2.11.6"
libraryDependencies += "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.0"