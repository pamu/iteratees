package enumerator

import iteratee.Iteratee

sealed trait Enumerator[E] { parent =>
  def apply[A](iteratee: Iteratee[E, A]): Iteratee[E, A]
}
