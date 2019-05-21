/**
 * Main.scala
 */

import scala.util.Failure
import scala.util.Success
import scala.util.Try

import scala.util.matching.Regex

/**
 * Try opening an HTTP connection to URL
 * Usage:
 * {{{
 * jdk6_tls_test URL
 * }}}
 *
 * Example:
 * {{{
 * jdk6_tls_test http://hostname
 * }}}
 */
object Main {
  def main(args: Array[String]) = {
    val arg: String = args(0)
    val isHttp: Regex = "https?://.*".r
    arg match {
      case isHttp() =>
        val url = new java.net.URL(arg)
        Try(url.openStream) match {
          case Success(in: java.io.InputStream) =>
            Console.println(s"Successfully connected to $arg")
          case Failure(e: Throwable) =>
            Console.err.println(s"Error: $e")
            // sys.exit(0)
        }
      case _ =>
        Console.err.println(s"Erorr: Expected URL got $arg")
    }
  }
}
