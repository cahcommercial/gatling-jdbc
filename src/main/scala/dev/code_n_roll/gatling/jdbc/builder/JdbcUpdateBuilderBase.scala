package dev.code_n_roll.gatling.jdbc.builder

import dev.code_n_roll.gatling.jdbc.action.JdbcUpdateActionBuilder
import io.gatling.core.session.Expression

case class JdbcUpdateBuilderBase(requestName: Expression[String]) {

  def table(name: Expression[String]) = JdbcUpdateValuesStep(requestName, name)

}

case class JdbcUpdateValuesStep(requestName: Expression[String], tableName: Expression[String]) {

  def values(values: Expression[String]) = JdbcUpdateWhereStep(requestName, tableName, values)

}

case class JdbcUpdateWhereStep(requestName: Expression[String], tableName: Expression[String], values: Expression[String]) {


  def where(where: Expression[String]) = JdbcUpdateActionBuilder(requestName, tableName, values, where)

}