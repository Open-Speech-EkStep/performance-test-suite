package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps
class LoadTestSimulations extends Simulation{
  //http config
  val httpProtocol = http
    // Here is the root for all relative URLs
    .baseUrl("https://meity-dev-asr.ulcacontrib.org/asr/v1/recognize")
    // Here are the common headers
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  // scenario
  val scn = scenario("Load Test")
    .exec(
      http("request_en")
        .post("/en")
        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
        .check(status is 200)
        .asJson
    )

  setUp(
    scn.inject(
      nothingFor(4), // 1
      atOnceUsers(0), // 2
      //      rampUsers(10).during(5 seconds), // 3
      constantUsersPerSec(1).during(2) // 4
      //      rampUsersPerSec(10).to(20).during(10)
    ).protocols(httpProtocol)
  );
  //  setUp(scn.inject(nothingFor(1),atOnceUsers(0), rampUsers(500) during(100 seconds)).protocols(httpProtocol))
  //  setUp(scn.inject(nothingFor(2),atOnceUsers(1), rampUsers(10) during(30 seconds)).protocols(httpProtocol))
  //  setUp(scn.inject(nothingFor(2),atOnceUsers(5), rampUsers(150) during(300 seconds)).protocols(httpProtocol))
}