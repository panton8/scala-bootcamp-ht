ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Scala-bootcamp-HT"
  )

name := "ControlStructures1Test"

version := "1.0"

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.2.0",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
)

name := "Basics"

version := "1.0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatestplus" %% "scalacheck-1-15" % "3.2.11.0" % Test,
)
