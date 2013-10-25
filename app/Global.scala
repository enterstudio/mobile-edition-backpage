import play.api.mvc.WithFilters

object Global extends WithFilters(conf.Metrics.RequestMetrics.asFilters: _*)