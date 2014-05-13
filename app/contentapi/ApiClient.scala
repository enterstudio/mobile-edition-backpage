package contentapi

import com.gu.openplatform.contentapi.FutureAsyncApi
import grizzled.slf4j.Logging
import scala.concurrent.ExecutionContext.global
import conf.{Timer, IpadBackpageConfiguration}
import scala.concurrent.Future
import com.gu.openplatform.contentapi.connection.HttpResponse
import play.api.libs.ws.WS
import scala.Some

object ApiClient extends FutureAsyncApi with Logging {
  implicit val executionContext = global

  apiKey = Some("mobile-edition-backpage")

  override val targetUrl = IpadBackpageConfiguration.ContentApi.targetUri

  def GET(urlString: String, headers: Iterable[(String, String)] = Nil): Future[HttpResponse] =
    Timer.timeFuture {
      WS.url(urlString).withHeaders(headers.toSeq: _*).get().map(r => HttpResponse(r.body, r.status, r.statusText))
    } map {
      case (result, time) =>
        info(s"GET $urlString in $time ms")
        result
    }

}
