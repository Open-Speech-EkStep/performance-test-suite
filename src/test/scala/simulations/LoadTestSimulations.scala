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
    .pause(2)
    .exec(
      http("request_en_1")
        .post("/en")
        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
        .check(status is 200)
        .asJson
    )
    .pause(2)
    .exec(
      http("request_en_2")
        .post("/en")
        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
        .check(status is 200)
        .asJson
    )
    .pause(2)
    .exec(
      http("request_en_3")
        .post("/en")
        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
        .check(status is 200)
        .asJson
    )
//    .pause(2)
//    .exec(
//      http("request_hi")
//        .post("/hi")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_bn")
//        .post("/bn")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_gu")
//        .post("/gu")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//         .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_kn")
//        .post("/kn")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_mai")
//        .post("/mai")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_mr")
//        .post("/mr")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_ne")
//        .post("/ne")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_or")
//        .post("/or")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_sa")
//        .post("/sa")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_ta")
//        .post("/ta")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_te")
//        .post("/te")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//          .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_pa")
//        .post("/pa")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_as")
//        .post("/as")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_doi")
//        .post("/doi")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_raj")
//        .post("/raj")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//
//    .pause(2)
//    .exec(
//      http("request_bho")
//        .post("/bho")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_ur")
//        .post("/ur")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//    .pause(2)
//    .exec(
//      http("request_ml")
//        .post("/ml")
//        .body(StringBody("""{"config": {"transcriptionFormat": {"value":"transcript"},"audioFormat": "wav","punctuation" : false,"enableInverseTextNormalization" : false},"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}""".stripMargin))
//        .check(status is 200)
//        .asJson
//    )
//  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(1000)).protocols(httpProtocol))
//  setUp(scn.inject(
//    rampConcurrentUsers(0) to(1) during(10 seconds),
//    constantConcurrentUsers(1) during(5 seconds)
//  ).protocols(httpProtocol))
  setUp(
    scn.inject(
      nothingFor(4), // 1
      atOnceUsers(10), // 2
      rampUsers(10).during(5), // 3
      constantUsersPerSec(20).during(15), // 4
      rampUsersPerSec(10).to(20).during(10), // 6
      rampUsersPerSec(10).to(20).during(10)
    ).protocols(httpProtocol)
  );
//  setUp(scn.inject(nothingFor(1),atOnceUsers(1), rampUsers(1) during(1 seconds)).protocols(httpProtocol))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(1), rampUsers(10) during(30 seconds)).protocols(httpProtocol))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(5), rampUsers(150) during(300 seconds)).protocols(httpProtocol))


}