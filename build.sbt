name := "hospitalapp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  anorm,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.apache.commons" % "commons-email" % "1.4",
  "mysql" % "mysql-connector-java" % "8.0.28",
  "javax.mail" % "mail" % "1.4.7"
)

play.Project.playJavaSettings

resolvers += "Maven Repo" at "https://repo1.maven.org/maven2/"
