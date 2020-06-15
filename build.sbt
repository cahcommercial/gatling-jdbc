organization := "com.cah.commtech"
name := "jdbc-gatling"
scalaVersion := "2.12.8"
crossPaths := false
libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.2.1",
  "io.gatling"            % "gatling-test-framework"    % "3.2.1",
  "org.scalikejdbc"       %% "scalikejdbc"              % "3.3.5",
  "com.h2database"        % "h2"                        % "1.4.198",
  "ch.qos.logback"        % "logback-classic"           % "1.2.3",
  "mysql"                 % "mysql-connector-java"      % "8.0.15"  % "test",
  "org.postgresql"        % "postgresql"                % "42.2.5"  % "test",
  "org.scalatest"         %% "scalatest"                % "3.0.6"   % "test",
  "org.testcontainers"    % "postgresql"                % "1.10.6"  % "test",
  "org.testcontainers"    % "mysql"                     % "1.10.6"  % "test"
)
enablePlugins(GatlingPlugin)

parallelExecution in Test := false

//everything below this line is related to the project release
homepage := Some(url("https://github.com/cahcommercial/gatling-jdbc"))
scmInfo := Some(ScmInfo(url("https://github.com/cahcommercial/gatling-jdbc"), "git@github.com:cahcommercial/gatling-jdbc.git"))
developers := List(Developer("g-fuse-knextors",
  "k-nextors team",
  "G-fuse-knextors-engineers@cardinalhealth.com",
  url("https://github.com/cahcommercial"))
)
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

githubOwner := "cahcommercial"
githubRepository := "gatling-jdbc"
githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")
