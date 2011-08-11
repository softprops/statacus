package statacus

import org.specs._

import java.net.{InetAddress => Addr, InetSocketAddress => SocketAddr}
import java.nio.channels.DatagramChannel

object StatsSpec extends Specification {
  implicit object Echo extends Pusher[String]  {
    def push(addr: SocketAddr, chan: DatagramChannel, stats: String*) = stats
  }
  "Stats" should {
    "know how to count" in {
      Stats("localhost").count()("stars", "seashells") must contain("stars:1|c") and contain("seashells:1|c")
    }
    "know how to count by increments" in {
      Stats("localhost").count(by = 10)("stars", "seashells") must contain("stars:10|c") and contain("seashells:10|c")
    }
    "increment a basic count" in {
      Stats("localhost").inc("fun") must contain("fun:1|c")
    }
    "increment a count by a specific number" in {
       Stats("localhost").inc("fun", by = 10) must contain("fun:10|c")
    }
    "prevent incrementing by a negative number" in {
       Stats("localhost").inc("fun", by = -3) must contain("fun:3|c")
    }
    "decrement a basic count" in {
      Stats("localhost").dec("pain") must contain("pain:-1|c")
    }
    "decrement a count by a specific a specific number" in {
      Stats("localhost").dec("pain", by = -10) must contain("pain:-10|c")
    }
    "prevent decrementing by a positive number" in {
      Stats("localhost").dec("pain", by = 10) must contain("pain:-10|c")
    }
    "time something" in {
      Stats("localhost").time("jetski", 230) must contain("jetski:230|ms")
    }
    "denote some ratio of samples" in {
      Stats("localhost").count(rate = 0.25)(
        (for(i <- (0 to 100)) yield "s-%d".format(i)): _*).size must beLessThan(26) and beGreaterThan(0)
    }
    "have convenient counters" in {
      val counter = Stats("localhost").counter("rejections")
      counter.inc must contain("rejections:1|c")
      counter.dec must contain("rejections:-1|c")
    }
  }
}
