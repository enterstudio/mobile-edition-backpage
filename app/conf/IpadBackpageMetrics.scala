package conf

import com.gu.management.TimingMetric

object IpadBackpageMetrics {
  val contentApi = new TimingMetric(
    "response_times",
    "contentapi",
    "Content API response times",
    "Content API response times"
  )
}
