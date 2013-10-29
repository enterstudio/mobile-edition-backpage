import com.gu.openplatform.contentapi.model.{Asset, Content}

package object contentapi {
  implicit class EnrichedContent(content: Content) {
    def mainImageAssets: Seq[Asset] = (for {
      elements <- content.elements
      mainImage <- elements find { element => element.`type` == "image" && element.relation == "main" }
    } yield mainImage.assets).toSeq.flatten
  }

  case class Dimensions(width: Int, height: Int) {
    lazy val aspectRatio = width.toFloat / height

    lazy val area = width * height
  }

  implicit class EnrichedAsset(asset: Asset) {

    private def typeDataInt(key: String) = asset.typeData.get(key).map(_.toInt)

    lazy val dimensions = for {
      width <- typeDataInt("width")
      height <- typeDataInt("height")
    } yield Dimensions(width, height)
  }
}
