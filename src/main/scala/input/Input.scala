package input

sealed trait Input[+E]

case object EOF extends Input[Nothing]

case object Empty extends Input[Nothing]

case class El[+E](elem: E) extends Input[E]
