package controllers

import play.api._
import play.api.mvc._
import lib.{ Story }
import scala.concurrent.ExecutionContext.Implicits.global
import contentapi.{MostViewedQuery, LatestItemQuery}

object Application extends Controller {
  def index = Action {
    Async {
      for {
        news <- LatestItemQuery.news.response
        sport <- LatestItemQuery.sport.response
        technology <- LatestItemQuery.technology.response
        comment <- LatestItemQuery.commentIsFree.response
        mostViewed <- MostViewedQuery.all.response
      } yield cacheHeaders(30, Ok(views.html.index(
        Story.singleStoryFrom(news),
        Story.singleStoryFrom(sport),
        Story.singleStoryFrom(technology),
        Story.singleStoryFrom(comment),
        Story.mostViewedStoriesFrom(mostViewed))))
    }
  }

  private def cacheHeaders[A](seconds: Int, result: SimpleResult[A]) = {
    // see http://www.ietf.org/rfc/rfc2616.txt & http://tools.ietf.org/html/rfc5861 for definitions of these headers
    result.withHeaders(
      "Cache-Control" -> s"max-age=$seconds, s-maxage=$seconds, stale-while-revalidate=$seconds, stale-if-error=345600"
    )
  }
}