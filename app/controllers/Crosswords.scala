package controllers

import play.api.mvc._
import lib.CacheHeaders

object Crosswords extends Controller {

  def upgrade = Action {
    CacheHeaders(30, Ok(views.html.crosswordsUpgrade()))
  }

}