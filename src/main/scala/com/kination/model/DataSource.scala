package com.kination.model

import scala.beans.BeanProperty

class DataSource (
                   @BeanProperty val host: String,
                   @BeanProperty val port: Int,
                   @BeanProperty val username: String,
                   @BeanProperty val password: String,
                   @BeanProperty val db: String,
                   @BeanProperty val table: String,
                 ) {
  override def toString = "%s:%d -> %s / %s".format(host, port, db, table)
}
