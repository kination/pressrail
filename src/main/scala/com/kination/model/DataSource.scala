package com.kination.model

import scala.beans.BeanProperty

class DataSource (
                   @BeanProperty val host: String,
                   @BeanProperty val port: String,
                   @BeanProperty val username: String,
                   @BeanProperty val password: String,
                   @BeanProperty val db: String,
                   @BeanProperty val table: String,
                 ) {
  override def toString = "%s:%s -> %s / %s".format(host, port, db, table)
}
