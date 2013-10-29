package lib

import org.specs2.mutable.Specification
import com.gu.openplatform.contentapi.Api
import scalax.io.Resource
import com.gu.openplatform.contentapi.model.ItemResponse
import contentapi.{Dimensions, EnrichedAsset}

class StorySpec extends Specification {
  "Story extracted from ItemResponse" should {
    "be the most recent story with an image, if one is available the response" in {
      // first item has no images
      val story = Story.singleStoryFrom(itemResponseFor("/contentapi/sample-commentisfree.you-tell-us.json")).get

      story.imageUri must beSome
      story.title mustEqual("Will you miss the Argos catalogue? | Paula Cocozza")
    }
    "be the most recent story (ie *a* story), if there is no story with an image available in the response" in {
      val story = Story.singleStoryFrom(itemResponseFor("/contentapi/sample-commentisfree.no-images-anywhere.json")).get

      story.title mustEqual("Ideas for 24-25 October")
    }
  }

  "Image chosen for story" should {
    "be of the most appropriate size" in {
      val story = Story.singleStoryFrom(itemResponseFor("/contentapi/sample-commentisfree.first-has-image.json")).get

      story.imageAsset.flatMap(_.dimensions) must beSome(Dimensions(460,276))
    }
  }

  def itemResponseFor(file: String): ItemResponse =
    Api.parseItem(Resource.fromInputStream(getClass.getResourceAsStream(file)).string)
}
