package conf

import com.gu.management._
import com.gu.management.logback.LogbackLevelPage

import grizzled.slf4j.Logging

object MobileFrontsManagement extends com.gu.management.play.Management with Logging {

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

  object RequestMetrics extends com.gu.management.play.RequestMetrics.Standard

  lazy val allMetrics = RequestMetrics.asMetrics

  lazy val pages = List(
    new ManifestPage,
    healthCheckPage,
    StatusPage(applicationName, allMetrics),
    new Switchboard(applicationName, Switches.all),
    new LogbackLevelPage(applicationName)
  )
}