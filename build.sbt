lazy val root = project
  .in(file("."))
  .aggregate(whyfp, todos)

lazy val whyfp = (project in file("why-fp")).settings(
  organization := "com.example",
  scalaVersion := "3.5.2",
  name         := "why-fp",
  wartremoverErrors ++= Warts.all,
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % V.catsEffect,
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % V.catsEffect,
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % V.catsEffect,
    // ZIO
    "dev.zio" %% "zio" % V.zioEffect,
    // ZIO Streams
    "dev.zio" %% "zio-streams" % V.zioEffect,
    // Cats Test
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    // ZIO Test
    "dev.zio" %% "zio-test"     % V.zioEffect % Test,
    "dev.zio" %% "zio-test-sbt" % V.zioEffect % Test
  ),
  scalacOptions ++= Seq("-deprecation")
)

lazy val todos = (project in file("todos")).settings(
  organization := "org.teckhooi",
  scalaVersion := "3.5.2",
  name         := "todos",
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % V.catsEffect,
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % V.catsEffect,
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % V.catsEffect,
    // Cats Test
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
  ),
  scalacOptions ++= Seq("-deprecation")
)

/*
enablePlugins(JavaAppPackaging)

val AttoVersion            = "0.7.0"
val Http4sVersion          = "0.23.11"
val CirceVersion           = "0.14.1"
val DoobieVersion          = "1.0.0-RC2"
val MunitVersion           = "0.7.20"
val MariaDBVersion         = "2.7.1"
val MySQLVersion           = "8.0.22"
val LogbackVersion         = "1.2.3"
val MunitCatsEffectVersion = "1.0.7"
val PureConfigVersion      = "0.14.0"
val SvmSubsVersion         = "20.2.0"
val CatsEffectTimeVersion  = "0.2.0"
val MeowMTLEffectsVersion  = "0.4.1"
val CaseInsensitiveVersion = "1.3.0"

lazy val root = (project in file("."))
  .settings(
    organization := "org.teckhooi",
    name := "apis-energy",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.13.9",
//    crossScalaVersions ++= Seq("2.13.5", "3.0.0"),
    libraryDependencies ++= Seq(
      "ch.qos.logback"         % "logback-classic"      % LogbackVersion,
      "com.github.pureconfig" %% "pureconfig"           % PureConfigVersion,
      "com.olegpy"            %% "meow-mtl-core"        % MeowMTLEffectsVersion,
      "io.chrisdavenport"     %% "cats-effect-time"     % CatsEffectTimeVersion,
      "io.circe"              %% "circe-generic"        % CirceVersion,
      "io.circe"              %% "circe-literal"        % CirceVersion,
      "io.circe"              %% "circe-parser"         % CirceVersion,
      "mysql"                  % "mysql-connector-java" % MySQLVersion,
      "org.mariadb.jdbc"       % "mariadb-java-client"  % MariaDBVersion,
      "org.http4s"            %% "http4s-ember-server"  % Http4sVersion,
      "org.http4s"            %% "http4s-ember-client"  % Http4sVersion,
      "org.http4s"            %% "http4s-circe"         % Http4sVersion,
      "org.http4s"            %% "http4s-dsl"           % Http4sVersion,
      "org.scalameta"         %% "svm-subs"             % SvmSubsVersion,
      "org.tpolecat"          %% "doobie-core"          % DoobieVersion,
      "org.tpolecat"          %% "doobie-h2"            % DoobieVersion,
      "org.tpolecat"          %% "doobie-hikari"        % DoobieVersion,
      "org.tpolecat"          %% "atto-core"            % AttoVersion,
      "org.typelevel"         %% "case-insensitive"     % CaseInsensitiveVersion,
      "org.typelevel"         %% "munit-cats-effect-3"  % MunitCatsEffectVersion % Test
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.2" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  )

scalacOptions ++= Seq("-deprecation", "-Ymacro-annotations")

reStart / mainClass := Some("org.teckhooi.apis.Main")

reStart / javaOptions += "-Xmx1g"

console / initialCommands += """import cats._
                                |import cats.effect._
                                |import cats.implicits._
                                |import doobie.util.ExecutionContexts
                                |import doobie.Read
                                |import doobie.implicits._
                                |import doobie._
                                |import doobie.util.fragment
                                |import doobie.implicits.javatimedrivernative._
                                |import doobie.util.transactor.Transactor
                                |import java.time.LocalDateTime
                                |import org.teckhooi.apis.domain._
                                |import  org.teckhooi.apis.repositories._
                                |implicit val cs = IO.contextShift(ExecutionContexts.synchronous)
                                """.stripMargin

 */
