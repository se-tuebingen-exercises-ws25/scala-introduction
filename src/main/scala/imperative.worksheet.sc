1 + 1

val s1 = "hello" + "world"
val s2 = "hello" + "world"

println(s1 == s2)

println(1 < 4)

println(true)


// immutable binding
val x = 1 + 1

println(x)


def sayHello(): Int = {
  println("hello")
  println("world")
   42
}

def loop(): Nothing = loop()

def foo(n: Int) = n + 1

def foo(s: String) = println(s)

def add(n: Int, m: Int) =
  val x = n + m * 2
  x * 3

val f: (Int, Int) => Int = (x, y) => x + y

def twice(value: Int, f: Int => Int): Int =
  f(f(value))

twice(42, (x) => x + 1)

val y = add(1, 2)

println(s"The result is ${y}")

//val result = if (math.random() > 0.2) {
//  sayHello() + sayHello()
//} else {
//  0
//}
//


val r = 1.until(10)
println(r.toList)

def triples(max: Int, sum: Int) =
  (1 until max).foreach { i =>
    for {
      j <- (i + 1) until max
      k <- (i + 1) until max
      if i + j + k == sum
    } {
      println(s"Found triple: ${i}, ${j}, ${k}")
    }
  }


val squares = for (i <- 1 until 10) yield {
  i + i
}

squares.toList.foreach(i => println(i))

squares.toList
  .map(x => x * 2)
  .map(x => x * 2)
  .foreach(x => println(x))

// Datentyp
enum IntList {
  // Cases
  case Nil
  case Cons(el: Int, rest: IntList)
}


def myIf(cond: Boolean)(thn: => Unit)(els: => Unit): Unit =
  if (cond) { thn } else { els }

myIf(3 == 5)(
  println("not equal")
)(
  println("equal")
)

def myforeach(l: IntList, f: Int => Unit): Unit = l match {
  case IntList.Nil => ()
  case IntList.Cons(el, rest) => f(el); myforeach(rest, f)
}

val scalaNil = Nil

val l = IntList.Cons(1, IntList.Cons(2, IntList.Nil))

myforeach(l, println)

val arr = Array.ofDim[String](10)

arr(2) = "test"

println(arr)

triples(10, 16)






