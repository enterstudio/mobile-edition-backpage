package lib

import com.gu.openplatform.contentapi.model.{ItemResponse, Content}
import org.joda.time.DateTime
import contentapi._

object Story {
  def fromContent(content: Content): Story = Story(
    content.webTitle,
    content.webPublicationDate,
    content.mainImageUri
  )

  implicit def fromItemResponse(itemResponse: ItemResponse): Option[Story] =
    itemResponse.results.headOption.map(fromContent)
}

case class Story(
  title: String,
  publishedAt: DateTime,
  imageUri: Option[String]
)