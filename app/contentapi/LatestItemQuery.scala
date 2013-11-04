package contentapi

trait QueryHelper {
  protected val ContentTypeTags = "type/article"

  protected val baseQuery = ApiClient.item.tag(ContentTypeTags).showElements("image")
}

object MostViewedQuery extends QueryHelper {
  val NumberOfStories = 10

  def all = baseQuery.pageSize(NumberOfStories).showMostViewed(true).itemId("")
}