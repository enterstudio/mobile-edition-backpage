package contentapi

trait QueryHelper {
  protected val ContentTypeTags = "type/article"

  protected val baseQuery = ApiClient.item.tag(ContentTypeTags).showElements("image")
}

object LatestItemQuery extends QueryHelper {
  private val singleItemQuery = baseQuery.pageSize(1)

  val news = singleItemQuery.itemId("uk-news")

  val sport = singleItemQuery.itemId("sport")

  val technology = singleItemQuery.itemId("technology")

  val commentIsFree = singleItemQuery.itemId("commentisfree")
}

object MostReadQuery extends QueryHelper {
  val NumberOfStories = 10

  val all = baseQuery.pageSize(NumberOfStories)
}