package controllers

import play.api.mvc._
import lib.{CacheHeaders, Boxes, Story}
import contentapi.MostViewedQuery
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

object Application extends Controller {
  def index = Action {
    Async {
      for {
        boxes <- Boxes.fetch()
        mostViewed <- MostViewedQuery.all.response
      } yield CacheHeaders(30, Ok(views.html.index(boxes, Story.mostViewedStoriesFrom(mostViewed))))
    }
  }
}