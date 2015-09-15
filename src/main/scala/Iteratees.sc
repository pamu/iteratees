import play.api.libs.iteratee.{Iteratee, Enumerator}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Test {
  println("Iteratees and Enumeratees")
  val fruits = Enumerator("mango", "apple", "fig",
    "black berry", "grapes")
  val moreFruits = Enumerator("custurd apple", "orange", "kiwi",
    "water melon")
  val result = fruits >>> moreFruits |>>
    Iteratee.fold[String, String]("")((r, c) => r + " -> " + c)
  val iteratee = Iteratee.flatten(result)
  val future = iteratee.run
  future onComplete {
    case Success(value) => println(s"value $value")
    case Failure(th) => println(s"throwable ${th.getMessage}")
  }
  Await.ready(future, 100 minutes)
}