package conf

import com.gu.management.TimingMetric

object Metrics {
  val contentApi = new TimingMetric(
    "response_times",
    "contentapi",
    "Content API response times",
    "Content API response times"
  )

  object RequestMetrics extends com.gu.management.play.RequestMetrics.Standard

  val all = Metrics.RequestMetrics.asMetrics :+ Metrics.contentApi
}
