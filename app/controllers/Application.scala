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
      } yield Ok(views.html.index(
        Story.singleStoryFrom(news),
        Story.singleStoryFrom(sport),
        Story.singleStoryFrom(technology),
        Story.singleStoryFrom(comment),
        Story.mostViewedStoriesFrom(mostViewed)))
    }
  }
}