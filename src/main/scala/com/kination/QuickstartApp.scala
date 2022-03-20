package com.kination

import com.kination.model.DataSource
import com.kination.util.YamlParser


object QuickstartApp {


  def main(args: Array[String]): Unit = {
    /* TODO: enable actor behavior later...
    val rootBehavior = Behaviors.setup[Nothing] { context =>
      val userRegistryActor = context.spawn(UserRegistry(), "UserRegistryActor")
      context.watch(userRegistryActor)

      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")

     */

    val parsed: DataSource = YamlParser.sourceFromFile("src/conf/dev/config-test.yml")
    new BinlogReader(parsed)

  }
}

