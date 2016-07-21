package enumeratee

import iteratee.Iteratee


sealed trait Enumeratee[From, To] { self =>
  def apply[A](e: Iteratee[To, A]): Iteratee[From, Iteratee[To, A]]
}
