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
    val scriptOutput = "/Users/pankaj/IdeaProjects/performaceTestSuite/src/streaming/executer.sh".!!
    println(s"""MyLoad Script invoked: ${scriptOutput} """)
    session

  })

//  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
  setUp(scn.inject(nothingFor(2),atOnceUsers(0), rampUsers(1000) during(5 seconds)))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(1), rampUsers(10) during(30 seconds)).protocols(httpProtocol))
//  setUp(scn.inject(nothingFor(2),atOnceUsers(5), rampUsers(150) during(300 seconds)).protocols(httpProtocol))


}