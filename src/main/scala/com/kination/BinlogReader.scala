package com.kination

import akka.event.slf4j.Logger
import com.github.shyiko.mysql.binlog.BinaryLogClient
import com.github.shyiko.mysql.binlog.event.{Event, EventHeaderV4, EventType}
import com.github.shyiko.mysql.binlog.event.deserialization.{
  EventDeserializer,
  ByteArrayEventDataDeserializer,
  NullEventDataDeserializer
}
import com.kination.model.DataSource


class BinlogReader(dataSource: DataSource) {

  val bc = new BinaryLogClient(
    dataSource.host,
    dataSource.port,
    dataSource.username,
    dataSource.password
  )
  val eventDeserializer = new EventDeserializer()
  val logger = Logger("BinlogReader")

  eventDeserializer.setCompatibilityMode(
    EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
    EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
  )

  eventDeserializer.setEventDataDeserializer(
    EventType.EXT_DELETE_ROWS,
    new ByteArrayEventDataDeserializer
  )

  eventDeserializer.setEventDataDeserializer(
    EventType.EXT_WRITE_ROWS,
    new NullEventDataDeserializer
  )

  bc.setEventDeserializer(eventDeserializer)
  bc.registerEventListener(new BinaryLogClient.EventListener() {
    def onEvent(event: Event): Unit = {
      val header: EventHeaderV4 = event.getHeader
      val eventType: EventType = header.getEventType

      eventType match {
        case et if EventType.isRowMutation(et) => EventHandler.queryParser(event) // row CRUD event
        case _ => // else
      }
    }
  })

  bc.connect()
}
