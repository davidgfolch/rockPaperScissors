# Rock Paper Scissors notes

Nice & funny test I've enjoyed, thank you!

Details of I+D and work done: 
- Five days of full-time work including one to decide witch technology to use.
  - Finally I've decided to use `scala-js`, but I've found documentation is poor & obsolete.
  - I did also thought about a Play+Scala (but I was not sure if I could use play for the tech test).
  - I've removed all reprecations from original seed project & update to last versions.
- Scala-js + Html5 + Css "Single Page Application".
- Responsive: it adapts to screen resolutions.
- Tests using uTest: I didn't develop much tests:
    - problem: I could not get scala-js dom.document instance in tests, so there is an example on how to do it decoupling dom from testable methods in UITest.
    - I tried to use a mock library (I had to change to scalatest framework to use mock frameworks) with no success due to dependencies nightmare & obsolete documentation.
    - Anyway, let you know I'm used to TDD.
- Artificial Intelligence:
    - Uses Factory pattern.
    - only AIRandom and AIFrequency are implemented at the moment.
    - when # of human players changes AI memory is reset.
- `MVC` architecture with singletons (scala objects)
    - `//todo` remove var(s) in [Model.scala](src/main/scala/com/dgf/rockPaperScissors/Model.scala)): 
- Game (see [Constants.scala](src/main/scala/com/dgf/rockPaperScissors/Constants.scala)):
    - Human & bots can play
        - together or not
        - up to 2 humans (due to keyboard space limitation)
        - bots with no humans always play in random mode
    - Two games implemented
        - `Rock,Paper,Scissors`
        - `Rock,Paper,Scissors, Spock,Lizard`

# Barebone application written in Scala.js -- not maintained

**This repository is not maintained anymore.
Consider the [scala-js-tutorial](https://github.com/scala-js/scalajs-tutorial) repository instead, along with [the actual tutorial](https://www.scala-js.org/tutorial/basic/).**

This is a barebone example of an application written in
[Scala.js](http://www.scala-js.org/).

## Get started

To get started, open `sbt` in this example project, and execute the task
`fastOptJS`. This creates the file `target/scala-2.12/example-fastopt.js`.
You can now open `index-fastopt.html` in your favorite Web browser!

During development, it is useful to use `~fastOptJS` in sbt, so that each
time you save a source file, a compilation of the project is triggered.
Hence only a refresh of your Web page is needed to see the effects of your
changes.

## Run the tests

To run the test suite, execute the task `test`. If you have installed
[Node.js](http://nodejs.org/), you can use that runtime to run the tests,
which is faster:

    > set scalaJSStage in Global := FastOptStage
    > test

## The fully optimized version

For ultimate code size reduction, use `fullOptJS`. This will take several
seconds to execute, so typically you only use this for the final, production
version of your application. While `index-fastopt.html` refers to the
JavaScript emitted by `fastOptJS`, `index.html` refers to the optimized
JavaScript emitted by `fullOptJS`.

If Node.js is installed, the tests can also be run in their fully optimized
version with:

    > set scalaJSStage in Global := FullOptStage
    > test

