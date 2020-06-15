package dev.code_n_roll.gatling.jdbc.action

case class JdbcUpdateActionBuilder(requestName: Expression[String], tableName: Expression[String], values: Expression[String], where: Expression[String]) extends ActionBuilder {

  override def build(ctx: ScenarioContext, next: Action): Action = {
    val statsEngine = ctx.coreComponents.statsEngine
    val clock = ctx.coreComponents.clock
    JdbcUpdateAction(requestName, tableName, values, where, clock, statsEngine, next)
  }

}
