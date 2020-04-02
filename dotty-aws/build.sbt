val dottyVersion = "0.23.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies +=  "software.amazon.awssdk" % "sdk-core" % "2.11.6",
    libraryDependencies +=  "software.amazon.awssdk" % "s3" % "2.11.6",
  )
