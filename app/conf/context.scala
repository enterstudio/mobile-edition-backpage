package conf

import com.gu.management._
import com.gu.management.logback.LogbackLevelPage
import scala.concurrent.{ExecutionContext, Future}

object Management extends com.gu.management.play.Management {

  override val applicationName = "mobile-edition-backpage"
  object Switches {
    val all = Seq(Healthcheck.switch)
  }

  /*
   * Always returns OK for now as required to keep app in the load balancer.
   */
  val healthCheckPage = new HealthcheckManagementPage {
    override def get(req: HttpRequest) = {
      Healthcheck.switch opt {
        PlainTextResponse("Ok")
      } getOrElse {
        ErrorResponse(503, "Service unavailable: healthcheck-enable switch is OFF")
      }
    }
  }

  lazy val pages = List(
    new ManifestPage,
    healthCheckPage,
    new Switchboard(applicationName, Switches.all),
    StatusPage(applicationName, Metrics.all),
    new LogbackLevelPage(applicationName)
  )
}

object Timer {
  def time[T](metric: TimingMetric, divisor: Long = 1, logger: grizzled.slf4j.Logger)(block: => T):T = {
    val (result, elapsed) = timeAndReturnDuration(block)
    val timeSpent = elapsed / math.max(divisor, 1)
    metric.recordTimeSpent(timeSpent)
    logger.info(s"Completed '${metric.title}' in $timeSpent ms")
    result
  }

  def time[T](block: => T)(processTime: Long => Unit): T = {
    val (result, elapsed) = timeAndReturnDuration(block)
    processTime(elapsed)
    result
  }

  def timeAndReturnDuration[T](block: => T): (T, Int) = {
    val start = System.currentTimeMillis
    val result = block
    val end = System.currentTimeMillis
    val elapsed = end - start
    (result, elapsed.toInt)
  }

  def timeFuture[T](block: => Future[T])(implicit executionContext: ExecutionContext): Future[(T, Int)] = {
    val start = System.currentTimeMillis
    val ftr = block

    ftr.map(_ -> (System.currentTimeMillis - start).toInt)
  }
}