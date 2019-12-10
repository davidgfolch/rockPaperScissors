package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.Constants._
import com.dgf.rockPaperScissors.Model.{addScore, getHumans, getPlayers, getWeaponsImageCredits, setHumanWeapon}
import com.dgf.rockPaperScissors.ai.Factory.Algorithm
import com.dgf.rockPaperScissors.helper.UIHelper._
import com.dgf.rockPaperScissors.helper.{UIHelper, UITemplate}
import org.scalajs.dom
import org.scalajs.dom.html.Document
import org.scalajs.dom.raw.{Element, HTMLSelectElement}

import scala.collection.immutable

object UI {

  private lazy implicit val doc: Document = dom.document
  private lazy val div: Element = doc.getElementById("container")
  private lazy val players: Element = doc.createElement("div")

  def createUI(): Unit = {
    setCss(UITemplate.CSS_STYLES)

    div.innerHTML=UITemplate.template
    doc.getElementById("algorithm").innerHTML = Algorithm.values.map((alg: Algorithm.algorithm) => s"<option value='${alg.id}'>$alg</option>").mkString
    doc.getElementById("games").innerHTML = Constants.games.map(game => s"<option value='${game._1}'>${game._2._1}</option>").mkString
    div.appendChild(players)

    dom.window.addEventListener("keyup", { e0: dom.Event =>
      val e = e0.asInstanceOf[dom.KeyboardEvent]
      if (!e.defaultPrevented && Model.isAcceptUserChooseWeapon && e.key.length==1) {
        e.preventDefault()
        e.stopImmediatePropagation()
        if (getHumans==2) {
          val player = if (e.location == 3) 2 else 1 //human player2 (numeric keyboard location==3)
          setHumanWeapon(player,Model.getWeapons(e.key.toInt-1).charAt(0)) //translate numbers to weapon keys
        } else setHumanWeapon(1,e.key.charAt(0))
      }
    }, true)

    paintPlayers(true)
    paintGameImage()
    enableAlgorithms()
    dom.window.onresize = (_: dom.Event) => {
      paintPlayers(false)
    }
    doc.getElementById("numPlayers").asInstanceOf[dom.raw.HTMLInputElement].focus()
  }

  def disableForm(): Unit = {
    disable("startButton")
    disable("numPlayers")
    disable("numHumans")
  }

  def enableForm(): Unit = {
    enable("startButton")
    enable("numPlayers")
    enable("numHumans")
    doc.getElementById("numPlayers").asInstanceOf[dom.raw.HTMLInputElement].focus()
  }

  def paintGameImage(): Unit = {
    doc.getElementById("gameImage").setAttrs(List(
      "src"->s"img/weapons/${Model.getWeaponsFolder}/game.svg",
      "alt"->"Game image",
      "title"->getWeaponsImageCredits))
    setInnerHtml("gameImageCredits",getWeaponsImageCredits)
  }

  def toggleGameImageView(): Unit = {
    val div=doc.getElementById("gameImageDiv")
    val className=if ("gameImage".equals(div.getAttribute("class"))) "Big" else ""
    setClass(div, s"gameImage$className")
    setClass("gameImage",s"gameImage$className")
    setClass("gameImageCredits",s"gameImageCredits$className")
  }

  def setHumanShortcuts(): Unit = {
    val shortCuts=doc.getElementById("humanShortcuts")
    setClass(shortCuts,if (getHumans==0) "hide" else "")
    shortCuts.innerHTML=if (getHumans==1) "Use initials: "+Model.getWeapons.map(w => w.charAt(0)).mkString(", ")+" (case sensitive)"
    else "Player 1: 1..9, Player2: 1..9 in numeric keyboard (respectively with names)"
  }

  def paintPlayers(create: Boolean): Unit = {
    val y = doc.getElementById("form").getBoundingClientRect().bottom.toInt + 10
    val radius = getMaxRadius(dom.window.innerWidth.toInt, dom.window.innerHeight.toInt, y)
    if (create) {
      if (Model.getScores.sum == 0) setClass("scoreList","hide")
      players.innerHTML = createPlayers(radius, y)
    } else updatePlayersCoord(radius, y)
  }

  def createPlayers(radius: (Int, Int), y: Int): String = {
    (1 to getPlayers).map(player => {
      val pos = calcCircleCoord(getPlayers, player, radius)
      val playerType = if (getHumans >= player) "human" else "pc"
      s"""<div id='player$player' style='left: ${PLAYERS_LEFT_MARGIN + pos._1}px; top: ${y + pos._2}px;' class='player'>
         |<img id='playerImg$player' src='img/player-$playerType.png' style='max-with: 2em; max-height: 2em'/>
         |<div>Player $player</div></div>""".stripMargin
    }).mkString
  }

  def updatePlayersCoord(radius: (Int, Int), y: Int): Unit = {
    (1 to getPlayers).foreach(player => {
      val pos = calcCircleCoord(getPlayers, player, radius)
      doc.getElementById(s"player$player") //todo remove style
        .setAttribute("style", s"position: fixed; left: ${PLAYERS_LEFT_MARGIN + pos._1}px; top: ${y + pos._2}px;")
    })
  }

  def paintCountDown(count: Int): Unit = {
    val div = doc.getElementById("countDown")
    val html = createCountDown(count: Int, dom.window.innerWidth.toInt, dom.window.innerHeight.toInt,
      players.getBoundingClientRect().top.toInt + 50)
    if (html.isDefined)
      div.innerHTML = html.get.charAt(0).toUpper+html.get.substring(1)
  }

  def createCountDown(count: Int, windowWidth: Int, windowHeight: Int, y: Int): Option[String] = {
    val html = if (count <= 0) None
    else Some(if (COUNT_DOWN_USE_WEAPON_NAMES) Model.getWeapons.apply(Model.getWeapons.size - count) else count.toString)
    html
  }

  def getMaxRadius(windowWidth: Int, windowHeight: Int, y: Int): (Int, Int) = {
    val availableWidth = windowWidth - PLAYERS_LEFT_MARGIN - PLAYER_WITH
    val availableHeight = windowHeight - y - PLAYER_HEIGHT
    (availableWidth / 2, availableHeight / 2)
  }

  def updatePlayers(playerWeapons: immutable.IndexedSeq[(Int, String)]): Unit = playerWeapons.foreach(playerWeapon => if (!playerWeapon._2.isEmpty) {
    doc.getElementById(s"playerImg${playerWeapon._1}").setAttribute("src", s"img/weapons/${Model.getWeaponsFolder}/${playerWeapon._2}.png")
  })

  def paintResults(results: Map[Int, Int]): Unit = {
    val countDown = doc.getElementById("countDown")
    countDown.innerHTML = ""
    if (results.isEmpty || (results.size > 1 && results.values.toSeq.distinct.size == 1)) {
      countDown.innerHTML = "Draw!"
    } else if (results.size > 2)
      results.foreach(playerScore => {
        val wins = doc.createElement("div")
        wins.innerHTML = s"${playerScore._2} wins"
        doc.getElementById(s"player${playerScore._1}").appendChild(wins)
        addScore(playerScore._1, playerScore._2)
      })
    else results.foreach(playerScore => addScore(playerScore._1, playerScore._2))
    paintWinners(results)
    paintScores()
  }

  def paintWinners(results: Map[Int, Int]): Unit = results.foreach(playerScore =>
    doc.getElementById(s"player${playerScore._1}").classList.add("winner")
  )

  def paintScores(): Unit = {
    val scoreList=doc.getElementById("scoreList")
    scoreList.innerHTML = "<table class='scores'><thead><th>Score</th><th>Player</th></thead>"+Model.getScores.zipWithIndex.sorted.reverse
      .map(playerScore => {
        val score = f"${playerScore._1}%04d"
        val player = f"${playerScore._2 + 1}%02d"
        s"<tr><td>$score</td><td>$player</td></tr>"
      }).mkString+"</table>"
    setClass(scoreList,"scoreList")
  }

  def message(msg: String): Unit = doc.getElementById("countDown").innerHTML=msg

  def enableAlgorithms(): Unit = {
    val select=doc.getElementById("algorithm").asInstanceOf[HTMLSelectElement]
    select.options.foreach(op =>
      if (getHumans==0 && !Algorithm.Random.id.toString.equals(op.value)) disable(op)
      else enable(op)
    )
    if (getHumans==0) select.selectedIndex=Algorithm.Random.id
  }

}
