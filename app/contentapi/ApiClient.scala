package contentapi

import com.gu.openplatform.contentapi.{ApiError, FutureAsyncApi}
import com.gu.openplatform.contentapi.connection.{HttpResponse, DispatchAsyncHttp}
import grizzled.slf4j.Logging
import scala.concurrent.ExecutionContext.global
import conf.{IpadBackpageMetrics, IpadBackpageConfiguration}
import scala.util.{Failure, Success}
import scala.concurrent.Future

object ApiClient extends FutureAsyncApi with DispatchAsyncHttp with Logging {
  implicit val executionContext = global

  override val targetUrl = IpadBackpageConfiguration.ContentApi.targetUri

  override protected def fetch(url: String, parameters: Map[String, String]) =
    super.fetch(url, IpadBackpageConfiguration.ContentApi.userTier map { userTier =>
      parameters + ("user-tier" -> userTier)
    } getOrElse parameters)

  override protected def get(urlString: String, headers: Iterable[(String, String)] = Nil): Future[HttpResponse] = {
    val startTime = System.currentTimeMillis
    lazy val elapsed = System.currentTimeMillis - startTime

    val response = super.get(urlString, headers)

    response onComplete {
      case Success(_) => {
        IpadBackpageMetrics.contentApi.recordTimeSpent(elapsed)
        debug(s"Successfully retrieved $urlString from Content API in ${elapsed}ms")
      }

      case Failure(error) => {
        IpadBackpageMetrics.contentApi.recordTimeSpent(elapsed)

        error match {
          case ApiError(statusCode, errorMessage) =>
            logger.error(s"Content API responded with $statusCode error for $urlString after " +
              s"${elapsed}ms: $errorMessage")
          case otherError =>
            logger.error(s"Unexpected error from Content API client for $urlString: ${otherError.getMessage}")
        }
      }
    }

    response
  }
}
