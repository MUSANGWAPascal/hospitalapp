name := "hospitalapp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  anorm,
  cache,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.apache.commons" % "commons-email" % "1.4"
)

//libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.28"

libraryDependencies += "javax.mail" % "mail" % "1.4.7"

play.Project.playJavaSettings
