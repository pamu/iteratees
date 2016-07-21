package iteratee

import step.Step

sealed trait Iteratee[E, +A] {
  def fold[B](folder: Step[E, A] => B): B
}
