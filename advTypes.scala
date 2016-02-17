implicit def strToInt(x: String) = x.toInt

class Container[A <% Int] { def addIt(x: A) = 123 + x }

println((new Container[String]).addIt("123"))

println((new Container[Int]).addIt(123)) 

println(List(1,2,3,4).min)

println(List(1,2,3,4).min(new Ordering[Int] { def compare(a: Int, b: Int) = b compare a }))

def foo[A](implicit x: Ordered[A]) {}
def foo1[A : Ordered] {}

println(implicitly[Ordering[Int]])
println(implicitly[Ordering[String]])