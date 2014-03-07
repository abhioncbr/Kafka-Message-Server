import sbt._
import Keys._
import AssemblyKeys._

name := "kafka-message-server-example"

crossPaths := false

libraryDependencies ++= Seq(
  "commons-cli"  % "commons-cli"   % "1.1"
)

libraryDependencies <<= (libraryDependencies) 

assemblySettings