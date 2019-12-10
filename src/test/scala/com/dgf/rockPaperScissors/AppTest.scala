package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.App._
import utest._

import scala.scalajs.js.JavaScriptException


object AppTest extends TestSuite {

  def tests: Tests = utest.Tests {

    'setNumPlayers {
//      // Initialize App
//      App.main()
      intercept[JavaScriptException](setNumPlayers("0")) //document not available
    }

  }

}
