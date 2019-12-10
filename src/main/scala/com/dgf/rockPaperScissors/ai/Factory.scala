package com.dgf.rockPaperScissors.ai

import scala.collection.immutable

object Factory {

  trait IAlgorithm {
    def process: String
    def update(humanBets: immutable.IndexedSeq[(Int,Int)]): Unit
  }

  object Algorithm extends Enumeration {
    type algorithm = Value
    val Random: Algorithm.Value = Value(0,"Random")
    val Frequency: Algorithm.Value = Value(1,"Frequency")
    val IocainePowder: Algorithm.Value = Value(2,"IocainePowder")
  }

  object AlgorithmFactory {
    def get(algorithm: Algorithm.Value): IAlgorithm = algorithm match {
      case Algorithm.Random => AIRandom
      case Algorithm.Frequency => AIFrequency
      case Algorithm.IocainePowder => AIIocainePowder
    }
  }

}
