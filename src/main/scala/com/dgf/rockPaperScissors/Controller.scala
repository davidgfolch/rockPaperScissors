package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.Constants._
import com.dgf.rockPaperScissors.Model.{getAlgorithm, getBeats, getHumanWeapon, getHumanWeapons, getHumans, getPlayers, getWeapons}
import com.dgf.rockPaperScissors.ai.Factory.AlgorithmFactory
import com.dgf.rockPaperScissors.helper.ControllerHelper

import scala.collection.immutable

object Controller {

  //todo iocainePowder implementation =)\

  def countDownStart(timerCallback: Int => Unit, timeoutCallback: () => Unit): Unit = {
    val countDown = if (COUNT_DOWN_USE_WEAPON_NAMES) getWeapons.size else 3
    val timeout = if (COUNT_DOWN_USE_WEAPON_NAMES) COUNT_TIMEOUT / getWeapons.size else COUNT_TIMEOUT
    timerCallback(countDown)
    ControllerHelper.countDownNext(countDown, timeout, timerCallback, timeoutCallback)
  }

  def applyAlgorithm(): Unit = {
    val isAI = getHumans!=getPlayers
    val algorithmImpl = AlgorithmFactory.get(getAlgorithm)
    val bets = if (isAI) (getHumans + 1 to getPlayers).map(player => (player, algorithmImpl.process))
    else immutable.IndexedSeq() //all players are humans
    val hBets = if (getHumans == 0) immutable.IndexedSeq() //no human players
    else (1 to getHumans).map(human => (human, getHumanWeapon(human - 1)))
    val fBets = hBets.++(bets)
    UI.updatePlayers(fBets) //todo remove UserInterface dependency
    if (bets.exists(_._2.isEmpty)) UI.message("Sorry, Algorithm not implemented!")
    else if (getHumans > 0 && getHumanWeapons.count(!_.isEmpty) != getHumans) {
      UI.message("Human player, please choose weapon!")
    } else {
      if (isAI) algorithmImpl.update(hBets.map(playerBet => (playerBet._1, getWeapons.indexOf(playerBet._2))))
      val results = computeWinners()(fBets)
      UI.paintResults(results)
    }
    UI.enableForm()
  }

  private def computeWinners()(implicit bets: immutable.IndexedSeq[(Int, String)]): Map[Int, Int] = {
    (1 to getPlayers).map(player1 =>
      (player1 to getPlayers).map(player2 =>
        if (player1 != player2 && bets(player1 - 1) != bets(player2 - 1)) {
          if (beatMatch(player1, player2)) player1
          else if (beatMatch(player2, player1)) player2
          else 0
        } else 0
      )).flatMap(m => m.filter(p => p != 0))
      .groupBy(identity).mapValues(_.size) //gets repeated wins for player count
  }

  private def beatMatch(p1: Int, p2: Int)(implicit bets: immutable.IndexedSeq[(Int, String)]): Boolean =
    getBeats.contains((bets(p1 - 1)._2.charAt(0), bets(p2 - 1)._2.charAt(0)))

}