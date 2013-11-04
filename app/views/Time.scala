package views

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, DateTimeZone}

object Time {
  def string =
    DateTimeFormat.forPattern("dd.MM.yy HH.mm").withZone(DateTimeZone.forID("Europe/London")).print(new DateTime())
}
