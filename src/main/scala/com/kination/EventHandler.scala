package com.kination

import akka.event.slf4j.Logger
import com.github.shyiko.mysql.binlog.event.{Event, EventHeaderV4, EventType}

object EventHandler {
  val logger = Logger("EventHandler")
  def init(): Unit = {

  }

  def queryParser(ev : Event): Unit = {
    val header: EventHeaderV4 = ev.getHeader
    val eventType: EventType = header.getEventType

    logger.info(s"Event -> ${ev.getData}")
    logger.info(s"getEventType -> ${eventType}")
  }

}
