name := "rockPaperScissors"
version := "0.1"
scalaVersion := "2.12.8"

// ScalaJS
enablePlugins(ScalaJSPlugin)
scalaJSUseMainModuleInitializer := true
mainClass in Compile := Some("com.dgf.rockPaperScissors.App")

// DOM
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
//jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

//Unit testing
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.1" % "test"
//libraryDependencies += "org.mockito" % "mockito-scala" % "1.9.0"
testFrameworks += new TestFramework("utest.runner.Framework")

//jsDependencies += RuntimeDOM
//scalaJSUseRhino in Global := false
//requiresDOM in Test := true
//jsDependencies += RuntimeDOM
