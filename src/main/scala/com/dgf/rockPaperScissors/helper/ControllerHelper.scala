package com.dgf.rockPaperScissors.helper

import scala.scalajs.js.timers.setTimeout

object ControllerHelper {

  var isCountDown=false

  def countDownNext(countDown: Int, timeout: Int, timerCallback: Int => Unit, timeoutCallback: () => Unit): Unit = {
    isCountDown=true
    //    val handler = setTimeout(COUNT_TIMEOUT) {
    setTimeout(timeout) {
      timerCallback(countDown-1)
      if (countDown-1 <= 0) {
        timeoutCallback()
        isCountDown=false
      } else countDownNext(countDown-1,timeout,timerCallback, timeoutCallback)
    }
    //    clearTimeout(handler)
  }

}
