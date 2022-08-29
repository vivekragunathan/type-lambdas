lazy val typeLambdas =
  project
    .in(file("."))
    .settings(
      name := "type-lambdas",
      version := "0.1.0-SNAPSHOT",
      organization := "scala.labs",
      scalaVersion := "2.12.16",
      scalacOptions ++= "-Ywarn-unused-import" :: "-deprecation" :: Nil,
      semanticdbEnabled := true,
      semanticdbVersion := scalafixSemanticdb.revision
    )
    .settings(
      libraryDependencies ++=
        "org.scala-lang"   % "scala-reflect" % "2.12.16" ::
          "org.typelevel" %% "cats-core"     % "2.8.0" ::
          "org.scalatest" %% "scalatest"     % "3.2.12" % Test ::
          Nil
    )
    .settings(
      resolvers ++=
        ("Typesafe" at "https://repo.typesafe.com/typesafe/releases/") ::
          ("Java.net Maven2 Repository" at "https://download.java.net/maven/2/") ::
          Nil
    )
