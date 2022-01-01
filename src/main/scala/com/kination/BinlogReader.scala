package com.kination

import akka.event.slf4j.Logger
import com.github.shyiko.mysql.binlog.BinaryLogClient
import com.github.shyiko.mysql.binlog.event.{Event, EventHeaderV4, EventType}
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer


object BinlogReader {

  def init () {
    val bc = new BinaryLogClient("localhost", 3306, "???", "???")
    val eventDeserializer = new EventDeserializer()
    val logger = Logger("log")

    eventDeserializer.setCompatibilityMode(
      EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
      EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
    )

    bc.setEventDeserializer(eventDeserializer)
    bc.registerEventListener(new BinaryLogClient.EventListener() {
      def onEvent(event: Event): Unit = {
        val header: EventHeaderV4 = event.getHeader
        val eventType: EventType = header.getEventType

        logger.info(s"Event -> ${event.getData}")
        logger.info(s"getEventType -> ${header.getEventType}")

        eventType match {
          case et if EventType.isRowMutation(et) => ??? // row CRUD event
          case _ => // else
        }
      }
    })

    bc.connect()
  }
}
