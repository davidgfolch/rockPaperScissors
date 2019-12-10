package com.dgf.rockPaperScissors.helper

object UITemplate {

  final val CSS_STYLES =
    """body { font-family: Verdana, Arial, Helvetica, sans-serif  }
      |h1 { font-size: 2vw }
      |.form { position: relative; float: left; border-radius: 2vw; padding: 1vw; border: 1px solid black; margin-right: 0.5vw; margin-bottom: 0.5vw }
      |.fieldset { position: relative; float: top}
      |.fieldset label { display:inline-block; width: 4.5em; margin-top: 0.5vw }
      |.fieldset input, select { margin-left: 1vw; margin-top: 0.5vw }
      |#humanShortcuts { color: green; padding-top: 0.5vw }
      |.winner { background-color: #0F0a !important; }
      |div.player { position: fixed;  border-radius: 2vw; padding: 0.5vw; text-align: center; background-color: #fffa }
      |div.player div { float: left, display: block }
      |div.gameImage { position: relative; float: left; border-radius: 2vw; border: 1px solid black; margin-right: 0.5vw }
      |img.gameImage { position: relative; float: left; max-height: 10vw; max-width: 10vw; margin-left: 0.5vw; cursor: pointer }
      |div.gameImageBig { position: absolute; top: 0px; left: 0px; max-width: 100%; background-color: #fff9; z-index: 1000; border-radius: 2vw; border: 1px solid black; margin: 1vw }
      |img.gameImageBig { max-width: 100%; background-color: #fff9; z-index: 1000; padding: 2vw; cursor: pointer  }
      |.gameImageCredits { font-size: 0.9vw; max-width: 10vw; margin: 0.5vw }
      |.gameImageCreditsBig { font-size: 1.5vw; margin: 0.5vw }
      |.scoreList { float: right; background-color: #000a; color: #0ff; border-radius: 2vw; padding: 1vw; }
      |table.scores { border-collapse: collapse; height: 60% }
      |table.scores th, td { border: 1px solid #0ff; border-collapse: collapse; text-align: center; }
      |table.scores tr:nth-child(even) {background-color: #888a }
      |#startButton { font-size: large }
      |div.countDown { font-size: 1.2vw; font-weight: bold; position: relative; float: top; text-align: center }
      |.hide { visibility: collapse; display: none }
      |""".stripMargin


  final val template: String =
  """
    |    <div id="scoreList" class="scoreList"></div>
    |    <div id="gameImageDiv" class='gameImage'>
    |     <img id="gameImage" src='' alt='' onclick="App().toggleGameImageView()" class="gameImage"/>
    |     <p id="gameImageCredits" class="gameImageCredits"></p>
    |    </div>
    |    <div>
    |     <form>
    |      <div class="form" id="form">
    |       <div class="fieldset">
    |          <label for="numPlayers">Players </label>
    |          <input id="numPlayers" type="number" min="2" step="1" value="2" onchange="App().setNumPlayers(value)"/>
    |        </div>
    |       <div class="fieldset">
    |          <label for="numHumans">Humans </label>
    |          <input id="numHumans" type="number" min="0" max="2" step="1" value="0" onchange="App().setNumHumans(value)"/>
    |          <div id='humanShortcuts' class='hide'></div>
    |        </div>
    |       <div class="fieldset">
    |         <label for="games">Games </label>
    |         <select id="games" onchange="App().setGame(value)"></select>
    |       </div>
    |       <div class="fieldset">
    |         <label for="algorithm">Algorithms </label>
    |         <select id="algorithm" onchange="App().setAlgorithm(value)"></select>
    |       </div>
    |      </div>
    |      <div class="form">
    |       <div id="countDown" class="countDown"></div>
    |       <input id="startButton" type="submit" value="Start!" onclick="App().countDownStart()"/>
    |      </div>
    |    </form>
    |   </div>
    |""".stripMargin

}
