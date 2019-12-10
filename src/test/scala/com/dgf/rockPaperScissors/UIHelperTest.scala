package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.helper.UIHelper.calcCircleCoord
import utest._


object UIHelperTest extends TestSuite {

  // Initialize App
//  App.main()

  def tests: Tests = utest.Tests {
    'calcCircleCoord {
      val coord=calcCircleCoord(2,1,(200,200))
      assert(coord==(180,200))
    }
  }

}
