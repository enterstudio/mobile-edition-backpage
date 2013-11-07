package lib

import com.github.theon.uri.Uri._

object CampaignUrl {
  def apply(plainUrl: String): String = plainUrl & ("INTCMP" -> "dis_231472")
}
