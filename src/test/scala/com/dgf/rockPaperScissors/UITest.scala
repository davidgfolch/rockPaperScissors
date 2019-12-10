package com.dgf.rockPaperScissors

import UI.createPlayers
import utest._


object UITest extends TestSuite {

//  // Initialize App
//  App.main()

  def tests: Tests = utest.Tests {
    'UI_Players {
      val html=createPlayers((100,100),100)
      assert(html!=null)
      assert(html.contains("id='player1'"))
      assert(html.contains("id='player2'"))
      assert(!html.contains("id='player3'"))
      assert(!html.contains("id='player0'"))
    }
  }

}
