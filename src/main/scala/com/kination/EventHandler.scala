package com.kination

import akka.event.slf4j.Logger
import com.github.shyiko.mysql.binlog.event.{Event, EventHeaderV4, EventType, WriteRowsEventData}

object EventHandler {
  val logger = Logger("EventHandler")
  def init(): Unit = {

  }

  def queryParser(event : Event): Unit = {
    val header: EventHeaderV4 = event.getHeader
    val eventType: EventType = header.getEventType

    logger.info(s"Event -> ${event.getData}")
    logger.info(s"getEventType -> ${eventType}")

    eventType match {
      case EventType.EXT_WRITE_ROWS => {
        val eventData: WriteRowsEventData = event.getData
        logger.info(s"${eventData.getRows}")
        eventData.getRows forEach(row => logger.info(s"${row.toString}"))
      }
      case _ => ???
    }
  }

}
