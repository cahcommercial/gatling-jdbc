package dev.code_n_roll.gatling.jdbc.action

import io.gatling.commons.util.Clock
import io.gatling.commons.validation.{Success, Validation}
import io.gatling.core.action.Action
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import scalikejdbc.{DB, SQL}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class JdbcUpdateAction(requestName: Expression[String],
                            tableName: Expression[String],
                            values: Expression[String],
                            where: Expression[String],
                            clock: Clock,
                            statsEngine: StatsEngine,
                            next: Action) extends JdbcAction {

  override def name: String = genName("jdbcUpdate")

  override def execute(session: Session): Unit = {
    val start = clock.nowMillis
    val validatedTableName = tableName.apply(session)
    val validatedValues = values.apply(session)
    val validatedWhere = where.apply(session)

    val result: Validation[Future[Unit]] = for {
      tableValue <- validatedTableName
      valuesValue <- validatedValues
      whereValue <- validatedWhere

      sql <- Success(s"UPDATE $tableValue SET $valuesValue WHERE $whereValue")
    } yield {
      Future {
        DB autoCommit { implicit session =>
          SQL(sql).execute().apply()
        }
      }
    }
    result.foreach(_.onComplete(result => {
      next ! log(start, clock.nowMillis, result, requestName, session, statsEngine)
    }))

    result.onFailure(e => throw new IllegalArgumentException(e))
  }
}
