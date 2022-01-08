package com.kination.util

import com.kination.model.DataSource
import org.yaml.snakeyaml.Yaml

import java.io.{File, FileInputStream}

object YamlParser {

  def fromYamlFile(configFilePath: String): java.util.Map[String, DataSource] = {
    val inputStream = new FileInputStream(new File(configFilePath))
    val yaml = new Yaml()
    yaml.load(inputStream).asInstanceOf[java.util.Map[String, DataSource]]
  }
}
