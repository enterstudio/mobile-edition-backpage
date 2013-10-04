import com.gu.openplatform.contentapi.model.Content

package object contentapi {
  implicit class EnrichedContent(content: Content) {
    /** I wonder how many times this exact same piece of code has been written */
    def mainImageUri = for {
      elements <- content.elements
      mainImage <- elements find { element => element.`type` == "image" && element.relation == "main" }
      firstAsset <- mainImage.assets.headOption
      uri <- firstAsset.file
    } yield uri
  }
}
