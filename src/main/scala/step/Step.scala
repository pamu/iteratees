package step

import input.Input

sealed trait Step[E, +A]

case class Done[E, +A](result: A, remaining: Input[E]) extends Step[E, A]

case class Error[E](th: Throwable, input: Input[E]) extends Step[E, Nothing]

case class Cont[E, +A](input: Input[E], next: Input[E] => Step[E, A]) extends Step[E, A]
