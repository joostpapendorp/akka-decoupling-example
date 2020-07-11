name := "template"
organization := "nl.papendorp"
version := "0.1-SNAPSHOT"

scalaVersion := "2.13.2"

lazy val akkaVersion = "2.6.5"
lazy val catsVersion = "2.1.0-RC1"
lazy val scalafxVersion = "14-R19"
lazy val slf4jVersion = "2.12.1"

lazy val scalaTestVersion = "3.1.2"
lazy val scalaTestPlusVersion = "3.2.0.1-M2"
lazy val scalaCheckVersion = "1.14.1"
lazy val scalaMockVersion = "4.4.0"

unmanagedClasspath in Runtime += baseDirectory.value / "src" / "main" / "resources"

scalacOptions ++= Seq(
	"-Xfatal-warnings",
	"-Ymacro-annotations",
	"-language:higherKinds",
	"-deprecation",
	"-unchecked",
	"-feature"
)

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
	"com.typesafe.akka" %% "akka-stream" % akkaVersion,

	"org.scalafx" %% "scalafx" % scalafxVersion,
	"org.scalafx" %% "scalafxml-core-sfx8" % "0.5",

	"org.typelevel" %% "cats-core" % catsVersion,
	"org.apache.logging.log4j" % "log4j-slf4j-impl" % slf4jVersion,

	"org.scalatest" %% "scalatest" % scalaTestVersion % Test,
	"org.scalatestplus" %% "scalacheck-1-14" % scalaTestPlusVersion % Test,
	"org.scalacheck" %% "scalacheck" % scalaCheckVersion % Test,

	"com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
	"com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,

	//http://scalamock.org/quick-start/
	"org.scalamock" %% "scalamock" % scalaMockVersion % Test
)
