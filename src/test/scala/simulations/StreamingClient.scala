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
    session

  })

  setUp(scn.inject(nothingFor(2),atOnceUsers(0), rampUsers(1) during(1 seconds)))

}