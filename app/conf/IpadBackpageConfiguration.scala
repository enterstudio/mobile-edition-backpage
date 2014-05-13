package conf

import play.api.Play

object IpadBackpageConfiguration {
  private val config = Play.current.configuration

  object ContentApi {
    val targetUri = config.getString("content_api_uri").getOrElse("http://content.guardianapis.com")
  }
}
