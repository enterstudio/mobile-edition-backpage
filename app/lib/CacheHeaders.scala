package lib

import play.api.mvc.SimpleResult

object CacheHeaders {
  def apply[A](seconds: Int, result: SimpleResult[A]) = {
    // see http://www.ietf.org/rfc/rfc2616.txt & http://tools.ietf.org/html/rfc5861 for definitions of these headers
    result.withHeaders(
      "Cache-Control" -> s"max-age=$seconds, s-maxage=$seconds, stale-while-revalidate=$seconds, stale-if-error=345600"
    )
  }
}
