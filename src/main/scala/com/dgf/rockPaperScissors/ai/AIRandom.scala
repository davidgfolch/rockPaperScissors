package com.dgf.rockPaperScissors.ai

import com.dgf.rockPaperScissors.Model
import com.dgf.rockPaperScissors.ai.Factory.IAlgorithm

import scala.collection.immutable
import scala.util.Random

object AIRandom extends IAlgorithm {

  private val rnd: Random = new Random()

  def process: String = Model.getWeapons(rnd.nextInt(Model.getWeapons.length))

  override def update(humanBets: immutable.IndexedSeq[(Int, Int)]): Unit = {}
}
