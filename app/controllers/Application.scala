package controllers

import play.api.mvc._
import lib.{Boxes, Story}
import contentapi.MostViewedQuery
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

object Application extends Controller {
  def index = Action {
    Async {
      for {
        boxes <- Boxes.fetch()
        mostViewed <- MostViewedQuery.all.response
      } yield cacheHeaders(30, Ok(views.html.index(boxes, Story.mostViewedStoriesFrom(mostViewed))))
    }
  }

  private def cacheHeaders[A](seconds: Int, result: SimpleResult[A]) = {
    // see http://www.ietf.org/rfc/rfc2616.txt & http://tools.ietf.org/html/rfc5861 for definitions of these headers
    result.withHeaders(
      "Cache-Control" -> s"max-age=$seconds, s-maxage=$seconds, stale-while-revalidate=$seconds, stale-if-error=345600"
    )
  }
}