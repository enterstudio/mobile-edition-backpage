package lib

import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global

case class Box(title: String, webUrl: String, story: Story)

object Boxes extends contentapi.QueryHelper {
  val boxIds = Seq("uk-news", "uk/sport", "technology","uk/commentisfree")
  
  private val itemsQuery = baseQuery.pageSize(5)

  def fetch(): Future[Seq[Box]] = {
    Future.sequence(for {
      boxId <-boxIds
    } yield for {
      apiResponse <- itemsQuery.itemId(boxId).response
    } yield for {
      section <- apiResponse.section
      story <- Story.singleStoryFrom(apiResponse)
    } yield Box(section.webTitle, section.webUrl, story)).map(_.flatten)
  }
}