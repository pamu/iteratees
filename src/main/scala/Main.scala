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
    val list = List(1, 2, 3, 4, 5)
    val result = foldLeft[Int, Int](list)(0)((r, c) => r * c)
    println(s"result $result")
  }

  def foldLeft[B, A](iStream: List[A])(acc: B)(f: (B, A) => B): B = {
    def loop(acc: B)(list: List[A]): B = list match {
      case Nil => acc
      case x :: Nil => loop(f(acc, x))(Nil)
      case x :: xs => loop(f(acc, x))(xs)
    }
    loop(acc)(iStream)
  }


  sealed trait Step[+B]

  case class Done[+B](result: B) extends Step[B]

  case class Cont[+A](value: A) extends Step[A]

  def foldLeftX[B, A](iStream: List[A])(acc: B)(f: (B, A) => Step[B]): B = {
    def loop(list: List[A])(acc: B): B = list match {
      case Nil => acc
      case x :: Nil => f(acc, x) match {
        case Done(result) => result
        case Cont(value) => loop(Nil)(value)
      }
    }
    loop(iStream)(acc)
  }

}
