package contentapi

trait QueryHelper {
  protected val ContentTypeTags = "type/article"

  protected val baseQuery = ApiClient.item.tag(ContentTypeTags).showElements("image")
}

object LatestItemQuery extends QueryHelper {
  private val itemsQuery = baseQuery.pageSize(5)

  def news = itemsQuery.itemId("uk-news")

  def sport = itemsQuery.itemId("uk/sport")

  def technology = itemsQuery.itemId("technology")

  def commentIsFree = itemsQuery.itemId("uk/commentisfree")
}

object MostViewedQuery extends QueryHelper {
  val NumberOfStories = 10

  def all = baseQuery.pageSize(NumberOfStories).showMostViewed(true).itemId("")
}