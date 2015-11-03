package controllers

import play.api.mvc._
import lib.{CacheHeaders, Boxes, Story}
import contentapi.MostViewedQuery
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

object Application extends Controller {
  def index(edition: String) = Action.async {
    for {
      boxes <- Boxes.fetch()
      mostViewed <- MostViewedQuery.all(edition).response
    } yield CacheHeaders(30, Ok(views.html.index(boxes, Story.mostViewedStoriesFrom(mostViewed))))
  }

  def redirectToIndex(edition: String) = Action {
    Redirect(routes.Application.index(edition))
  }
}