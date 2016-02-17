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

trait Container1[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }

val container = new Container1[List] { def put[A](x: A) = List(x); def get[A](m: List[A]) = m.head }

println(container.put("hey"))

println(container.put(123))

trait Container2[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }

implicit val listContainer = new Container2[List] { def put[A](x: A) = List(x); def get[A](m: List[A]) = m.head }

implicit val optionContainer = new Container2[Some] { def put[A](x: A) = Some(x); def get[A](m: Some[A]) = m.get }

def tupleize[M[_]: Container2, A, B](fst: M[A], snd: M[B]) = {
      val c = implicitly[Container2[M]]                             
      c.put(c.get(fst), c.get(snd))
      }
println(tupleize(Some(1), Some(2)))
println(tupleize(List(1), List(2)))

def foo(x: { def get: Int }) = 123 + x.get

println(foo(new { def get = 10 }))

trait Foo[M[_]] { type t[A] = M[A] }

val x: Foo[List]#t[Int] = List(1)

class MakeFoo[A](implicit manifest: Manifest[A]) { def make: A = manifest.erasure.newInstance.asInstanceOf[A] }

(new MakeFoo[String]).make