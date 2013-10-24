import org.specs2.mutable._
import org.specs2.runner._

import play.api.test._
import play.api.test.Helpers._

class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo(s"http://localhost:$port/ipad-backpage.html")

      browser.pageSource must contain("From guardian.co.uk")
    }
  }
}