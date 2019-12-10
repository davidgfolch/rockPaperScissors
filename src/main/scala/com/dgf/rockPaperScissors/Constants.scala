package com.dgf.rockPaperScissors

object Constants {

  final val COUNT_DOWN_USE_WEAPON_NAMES = true
  final val COUNT_TIMEOUT = 3000
  final val PLAYERS_LEFT_MARGIN = 20
  final val PLAYER_WITH = 80
  final val PLAYER_HEIGHT = 90

  val games:Map[String,(String,String,List[String],List[(Char,Char)])] = Map(
    "RPS"-> ("Rock Paper Scissors",
      "Image By Enzoklop - Own work, CC BY-SA 3.0, <a href='https://commons.wikimedia.org/w/index.php?curid=27958688' target='_blank'>Wikimedia.org</a>",
      List("rock", "paper", "scissors"),
      List(('r', 's'), ('p', 'r'), ('s', 'p'))),
    "rpsSl"-> ("Rock Paper Scissors Spock Lizard",
      "By DMacks (talk) - Edited (repositioned elements) version of File:Pierre ciseaux feuille lézard spock.svg, which is by Nojhan and licensed GFDL/cc-by-sa-3.0,2.5,2.0,1.0, CC BY-SA 3.0, <a href='https://commons.wikimedia.org/w/index.php?curid=13241299' target='_blank'>Wikimedia.org</a>",
      List("rock", "paper", "scissors", "Spock", "lizard"),
      List(('r', 's'), ('r', 'l'), ('p', 'r'), ('p', 'S'), ('s', 'p'), ('s', 'l'), ('S','s'),('S','r'),('l','S'),('l','p')))
  )

}
