import play.api.libs.iteratee.{Iteratee, Enumerator}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
 * Created by pnagarjuna on 12/09/15.
 */
object Main {
  def main(args: Array[String]): Unit = {
    val languages = Enumerator("scala", "java", "haskel", "clojure")
    val iteratee = Iteratee.fold[String, String]("")((r, c) => s"$r => $c ")
    val result = languages.run(iteratee)
    result.onComplete {
      case Success(value) => println(value)
      case Failure(th) => println(th.getMessage)
    }
    Await.result(result, 10 minutes)
  }
}
