package com.dgf.rockPaperScissors

import com.dgf.rockPaperScissors.ai.Factory.Algorithm

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

object App {

  @JSExportTopLevel("App")
  protected def getInstance(): this.type = this

  @JSExport
  def main(): Unit = UI.createUI()

  @JSExport
  def setNumPlayers(value: String): Unit = {
    if (!value.isEmpty && Model.getPlayers!=value.toInt) {
      Model.setPlayers(value.toInt)
      UI.paintPlayers(true)
    }
  }

  @JSExport
  def setNumHumans(value: String): Unit = {
    if (!value.isEmpty && Model.getHumans!=value.toInt) {
      Model.setHumans(value.toInt)
      UI.enableAlgorithms()
      UI.setHumanShortcuts()
      UI.paintPlayers(true)
    }
  }

  @JSExport
  def setGame(value: String): Unit = {
    Model.setGame(value)
    UI.setHumanShortcuts()
    UI.paintGameImage()
  }

  @JSExport
  def setAlgorithm(value: String): Unit = Model.setAlgorithm(Algorithm.apply(value.toInt))

  @JSExport
  def countDownStart(): Unit = {
    UI.disableForm()
    Model.resetHumanWeapons()
    UI.paintPlayers(true)
    Controller.countDownStart(UI.paintCountDown, Controller.applyAlgorithm)
  }

  @JSExport
  def toggleGameImageView(): Unit = {
    UI.toggleGameImageView()
  }

}
