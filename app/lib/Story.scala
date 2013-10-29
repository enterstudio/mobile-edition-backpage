package lib

import com.gu.openplatform.contentapi.model.{Asset, ItemResponse, Content}
import org.joda.time.DateTime
import contentapi._
import scala.language.implicitConversions

object Story {

  implicit val PreferredImageAssetsOrdering = {
    implicit val preferredDimensionsOrdering = {
      import Numeric.Implicits._

      val preferred = Dimensions(460, 276)

      Ordering.by { d: Dimensions =>
        def diff[N: Numeric](f: Dimensions => N) = (f(d) - f(preferred)).abs

        (diff(_.area), diff(_.aspectRatio))
      }
    }

    Ordering.by { a: Asset => a.dimensions}
  }

  def fromContent(content: Content): Story = Story(
    content.webTitle,
    content.webUrl,
    content.webPublicationDate,
    content.mainImageAssets.sorted.headOption
  )

  implicit val StoryOrdering = {
    implicit val dateTimeOrdering = new Ordering[DateTime] {
      def compare(a: DateTime, b: DateTime) = a.compareTo(b)
    }

    Ordering.by { story: Story => (story.imageUri.isDefined, story.publishedAt) }.reverse
  }

  implicit def mostViewedStoriesFrom(itemResponse: ItemResponse): Seq[Story] =
    itemResponse.mostViewed.map(fromContent)

  implicit def singleStoryFrom(itemResponse: ItemResponse): Option[Story] =
    itemResponse.results.map(fromContent).sorted.headOption
}

case class Story(
  title: String,
  webUrl: String,
  publishedAt: DateTime,
  imageAsset: Option[Asset]
) {
  lazy val imageUri = imageAsset.map(_.file)
}