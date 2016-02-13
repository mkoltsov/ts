def fn(a:Int, b:Int) = a+b

val curriedFn = (fn _).curried

val a = curriedFn(1)

println(a(5))

class AddOne extends (Int => Int) {
  def apply(m: Int): Int = m + 1
}

val one: PartialFunction[Int, String] = { case 1 => "one" }

val two: PartialFunction[Int, String] = { case 2 => "two" }

val three: PartialFunction[Int, String] = { case 3 => "three" }

val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

val partial = one orElse two orElse three orElse wildcard