package simulations

import java.io._
import sys.process._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class StreamingClient extends Simulation{
  // scenario
  val scn = scenario("Load Test")
  .exec(session => {
    val scriptOutput = "sh /Users/pankaj/gitAll/performance-test-suite/streaming/executer.sh".!!
    println(s"""MyLoad Script invoked: ${scriptOutput} """)
//
//    println(s"""MyLoad Script invoked: ${scriptOutput4} """)
    session

  })
//   setUp(
//      scn.inject(
//        nothingFor(4), // 1
//        atOnceUsers(10), // 2
//        rampUsers(10).during(5), // 3
//        constantUsersPerSec(20).during(1800), // 4
//        rampUsersPerSec(10).to(20).during(10), // 6
//        rampUsersPerSec(10).to(20).during(10)
//      )
//    );
//  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
  setUp(scn.inject(nothingFor(2),atOnceUsers(0), rampUsers(1) during(1 seconds)))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(1), rampUsers(10) during(30 seconds)).protocols(httpProtocol))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(5), rampUsers(150) during(300 seconds)).protocols(httpProtocol))


}