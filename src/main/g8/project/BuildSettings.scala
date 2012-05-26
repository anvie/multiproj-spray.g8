import sbt._
import Keys._
import com.github.siasia.WebPlugin._
import ls.Plugin._

object BuildSettings {

  lazy val basicSettings = seq(
    version               := "$version$",
    homepage              := Some(new URL("http://ansvia.com")),
    organization          := "com.$organization$",
    organizationHomepage  := Some(new URL("http://ansvia.com")),
    description           := "$description$",
    startYear             := Some(2012),
    licenses              := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion          := "2.9.1",
    resolvers             ++= Dependencies.resolutionRepos,
    scalacOptions         := Seq("-deprecation", "-encoding", "utf8")
  )

  lazy val moduleSettings = basicSettings ++ seq(
    // scaladoc settings
    (scalacOptions in doc) <++= (name, version).map { (n, v) => Seq("-doc-title", n, "-doc-version", v) },

    // publishing
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    crossPaths := false,
    publishMavenStyle := true,

    // LS
    (LsKeys.tags in LsKeys.lsync) := Seq("http", "server", "client", "async"),
    (LsKeys.docsUrl in LsKeys.lsync) := Some(new URL("http://spray.github.com/spray/api/spray-can/")),
    (externalResolvers in LsKeys.lsync) := Seq("spray repo" at "http://repo.spray.cc")
  )

  lazy val noPublishing = seq(
    publish := (),
    publishLocal := ()
  )

}

