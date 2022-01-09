package com.kination.util

import com.kination.model.DataSource
import cats.syntax.either._
import io.circe._
import io.circe.generic.auto._
import io.circe.yaml


object YamlParser {
  def sourceFromFile(configFilePath: String): DataSource = {
    val lines = scala.io.Source.fromFile(configFilePath).mkString.stripMargin
    val json: Either[ParsingFailure, Json] = yaml.parser.parse(lines)
    json.leftMap(err => err).flatMap(_.as[DataSource]).valueOr(throw _)
  }
}
