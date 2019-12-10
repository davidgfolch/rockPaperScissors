package com.dgf.rockPaperScissors.helper

import org.scalajs.dom
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.Document
import org.scalajs.dom.raw.{Element, Node}

import scala.scalajs.js

object UIHelper {

  def setCss(cssStyles: String)(implicit doc:Document): Node = doc.getElementsByTagName("head")(0)
    .appendChild(addElement("style", List("type" -> "text/css"), Some(cssStyles)))


  @js.native
  trait EventName extends js.Object {
    type EventType <: dom.Event
  }

  object EventName {
    def apply[T <: dom.Event](name: String): EventName {type EventType = T} = name.asInstanceOf[EventName {type EventType = T}]

    val onmousedown: EventName {type EventType = MouseEvent} = apply[dom.MouseEvent]("onmousedown")
  }

  @js.native
  trait ElementExt extends js.Object {
    def addEventListener(name: EventName)(
      f: js.Function1[name.EventType, _]): Unit
  }

  def addElement(tagName: String, attrs: List[(String, String)], innerHtml: Option[String]=None)(implicit doc:Document): Element = {
    val elm=doc.createElement(tagName)
    attrs.foreach(attr => elm.setAttribute(attr._1, attr._2))
    if (innerHtml.isDefined) elm.innerHTML=innerHtml.get
    elm
  }

  implicit class elemExt(val elm: Element) {
    def setAttrs(attrs: List[(String, String)]): Element = {
      attrs.foreach(attr => elm.setAttribute(attr._1, attr._2))
      elm
    }
  }

  def setClass(id: String, className: String)(implicit doc:Document): Unit = doc.getElementById(id).setAttribute("class",className)
  def setClass(elm: Element, className: String): Unit = elm.setAttribute("class",className)

  def disable(id: String)(implicit doc:Document): Unit = disable(doc.getElementById(id))
  def disable(elm: Element)(implicit doc:Document): Unit = elm.setAttribute("disabled", "disabled")
  def enable(id: String)(implicit doc:Document): Unit = enable(doc.getElementById(id))
  def enable(elm: Element)(implicit doc:Document): Unit = elm.removeAttribute("disabled")

  def setInnerHtml(id: String, innerHtml: String)(implicit doc:Document): Unit = doc.getElementById(id).innerHTML=innerHtml

  def calcCircleCoord(totalItems: Int, current: Int, radius: (Int,Int)): (Int,Int) = {
    val pi=if (totalItems<4) Math.PI*2/totalItems*(current-1) else Math.PI*2/totalItems*current
    val radX=radius._1*totalItems/20
    val radY=radius._2*totalItems/20
    val fRadX=if (radX>radius._1) radius._1 else radX
    val fRadY=if (radY>radius._2) radius._2 else radY
    val x=(radius._1-Math.cos(pi)*fRadX).toInt
    val y=(radius._2+Math.sin(-pi)*fRadY).toInt
    (x,y)
  }

  def log(msg: String): Unit = dom.window.console.log(msg)

}
