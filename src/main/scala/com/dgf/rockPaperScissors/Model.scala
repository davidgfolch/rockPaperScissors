package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.ai.Factory.Algorithm
import com.dgf.rockPaperScissors.helper.ControllerHelper

import scala.collection.immutable

object Model {

  private var game = Constants.games.keys.head
  private var weapons = Constants.games(game)._3
  private var beat = Constants.games(game)._4
  private var players: Int = 2
  private var humans: Int = 0
  private var scores: List[Int] = List.fill(players)(0)
  private var algorithm: Algorithm.Value = Algorithm.Random
  private var humanWeapons: Seq[String] = Seq.fill(humans) {""}

  def getWeapons: immutable.Seq[String] = weapons

  def getWeaponsFolder: String = game

  def getWeaponsImageCredits: String = Constants.games(game)._2

  def getBeats: immutable.Seq[(Char, Char)] = beat

  def setGame(name: String): Unit = {
    game = name
    weapons = Constants.games(game)._3
    beat = Constants.games(game)._4
  }

  def setPlayers(n: Int): Unit = {
    players = n
    scores = List.fill(players)(0)
  }

  def setHumans(n: Int): Unit = {
    humans = n
    humanWeapons = Seq.fill(n) {
      ""
    }
  }

  def getHumans: Int = humans

  def getPlayers: Int = players

  def setAlgorithm(value: Algorithm.algorithm): Unit = algorithm = value

  def getAlgorithm: Algorithm.algorithm = algorithm

  def getScores: List[Int] = scores

  def addScore(player: Int, score: Int): Unit = scores = scores.updated(player - 1, scores(player - 1) + score)

  def setHumanWeapon(player: Int, initial: Char): Unit = if (isAcceptUserChooseWeapon) {
    val weapon = getWeapons.find(w => w.charAt(0) == initial)
    if (weapon.isDefined) humanWeapons = humanWeapons.updated(player - 1, weapon.get)
  }

  def getHumanWeapon(player: Int): String = humanWeapons(player)
  def getHumanWeapons: Seq[String] = humanWeapons

  def resetHumanWeapons(): Unit = humanWeapons = Seq.fill(humans) {""}

  def isAcceptUserChooseWeapon: Boolean = ControllerHelper.isCountDown && humans > 0

}
