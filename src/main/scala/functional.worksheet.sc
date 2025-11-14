
enum TrafficLight {
  case Red
  case Yellow
  case Green
}

import TrafficLight.{Green, Red, Yellow}

val exTrafficLight = TrafficLight.Red


def myExample(t: TrafficLight) = t match {
  case TrafficLight.Red => ???
  case TrafficLight.Yellow => ???
  case TrafficLight.Green => ???
}


case class Person(name: String, favoriteFood: String)

val me = Person("Jonathan", "Burger")

println(me.name)
println(me.favoriteFood)

me match {
  case Person(n, fsdfsdfsd) => println(n)
}

case class Crossing(horizontal: TrafficLight, vertical: TrafficLight)

def allowedConfig(crossing: Crossing): Boolean =
  crossing match {
    //case Crossing(h, v) if h == v => false
    case Crossing(h, v) if h == v => false
    case _ => true
  }

// first-class function
val increment = (x: Int) => x + 1

// higher-order function
// (Int => Int) => Int => Int
def id(f: Int => Int): Int => Int = f

val same = id(id(increment))

same(42)

val l2 = 1 :: 2 :: Nil
val l3 = 1 :: 3 :: Nil

l2 == l3


import scala.collection.mutable
