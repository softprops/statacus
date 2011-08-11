organization := "me.lessis"

name := "statacus"

version := "0.1.0-SNAPSHOT"

crossScalaVersions ++= Seq("2.8.0", "2.8.1", "2.9.0", "2.9.0-1", "2.9.1.RC1", "2.9.1.RC2")

libraryDependencies <+= scalaVersion(sv =>
  sv.split('.').toList match {
    case "2" :: "8" :: _ => "org.scala-tools.testing" % "specs_2.8.1" % "1.6.8"
    case "2" :: "9" :: "1" :: _ => "org.scala-tools.testing" % "specs_2.9.0-1" % "1.6.8"
    case "2" :: "9" :: _ => "org.scala-tools.testing" %% "specs" % "1.6.8"
    case _ => error("specs not supported for scala version %s" format sv)
  }
)
