package lib

import contentapi.ApiClient

object LatestItemQuery {
  val ContentTypeTags = "(type/article|type/gallery|type/video|type/audio),-type/audioslideshow"

  private val baseQuery = ApiClient.item.tag(ContentTypeTags).showElements("image").pageSize(1)

  val news = baseQuery.itemId("news")

  val sport = baseQuery.itemId("sport")

  val technology = baseQuery.itemId("technology")

  val commentIsFree = baseQuery.itemId("commentisfree")
}
