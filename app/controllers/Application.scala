package controllers

import play.api._
import play.api.mvc._
import lib.{Story}
import scala.concurrent.ExecutionContext.Implicits.global
import contentapi.LatestItemQuery

object Application extends Controller {
  def index = Action.async {
    for {
      news <- LatestItemQuery.news.response
      sport <- LatestItemQuery.sport.response
      technology <- LatestItemQuery.technology.response
      comment <- LatestItemQuery.technology.response
    } yield Ok(views.html.index(
      Story.fromItemResponse(news),
      Story.fromItemResponse(sport),
      Story.fromItemResponse(technology),
      Story.fromItemResponse(comment)
    ))
  }
}