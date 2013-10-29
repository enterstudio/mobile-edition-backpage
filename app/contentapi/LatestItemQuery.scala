package contentapi

trait QueryHelper {
  protected val ContentTypeTags = "type/article"

  protected val baseQuery = ApiClient.item.tag(ContentTypeTags).showElements("image")
}

object LatestItemQuery extends QueryHelper {
  private val itemsQuery = baseQuery.pageSize(5)

  val news = itemsQuery.itemId("uk-news")

  val sport = itemsQuery.itemId("uk/sport")

  val technology = itemsQuery.itemId("technology")

  val commentIsFree = itemsQuery.itemId("uk/commentisfree")
}

object MostViewedQuery extends QueryHelper {
  val NumberOfStories = 10

  val all = baseQuery.pageSize(NumberOfStories).showMostViewed(true).itemId("")
}