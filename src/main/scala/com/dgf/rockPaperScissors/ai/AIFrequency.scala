package com.dgf.rockPaperScissors.ai

import com.dgf.rockPaperScissors.Model
import com.dgf.rockPaperScissors.ai.Factory.IAlgorithm
import com.dgf.rockPaperScissors.helper.UIHelper

import scala.collection.immutable
import scala.util.Random

object AIFrequency extends IAlgorithm {

  private val rnd: Random = new Random()

  private final var humanMoves:Map[Int,Array[Int]]=Map()

  override def process: String = {
    if (Model.getHumans!=humanMoves.size) {
      UIHelper.log("reset AIFrecuency memory")
      humanMoves=Map()
      (1 to Model.getHumans).foreach(human=>humanMoves += (human -> Array.fill(Model.getWeapons.size)(0)))
      UIHelper.log("moves="+humanMoves)
      AIRandom.process
    } else {
      val maxPlayersBets =humanMoves.map(playerMoves=>playerMoves._2.indexOf(humanMoves(playerMoves._1).max)).toSeq
      val maxPlayerBetRandom=Model.getWeapons(maxPlayersBets(rnd.nextInt(Model.getHumans))) //just can win to one player
      val beat=Model.getBeats.find(beat=>beat._2.equals(maxPlayerBetRandom.charAt(0))).get._1
      Model.getWeapons.find(b=>b.charAt(0).equals(beat)).get
    }
  }

  override def update(humanBets: immutable.IndexedSeq[(Int, Int)]): Unit =
    humanBets.foreach(playerBet=>humanMoves(playerBet._1)(playerBet._2)+=1)
}
