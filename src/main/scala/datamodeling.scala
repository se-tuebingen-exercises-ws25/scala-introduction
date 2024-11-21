package demo

// In Info 1 you learnt about sumtypes (also sometimes called "union types").
// For example:
//
// A traffic light is one of:
// - red
// - yellow
// - green

// In Scala, we have multiple ways of modeling this.
// 1) As an enumeration (e.g., enum ... { case A; case B })
// 2) By using type union (e.g., A | B)
// 3) By using subtyping.

// 1) Enumeration
// --------------
// Also see object demoEnumeration in file fp.scala (repeated here for convencience):
object demoEnumerationAgain {

  // 1) Enumeration
  enum TrafficLight {
    case Red
    case Yellow
    case Green
  }

  def switchTrafficLight(t: TrafficLight): TrafficLight = t match {
    case TrafficLight.Red => TrafficLight.Green
    case TrafficLight.Yellow => TrafficLight.Red
    case TrafficLight.Green => TrafficLight.Yellow
  }

  println(switchTrafficLight(TrafficLight.Green)) // Yellow
}

// 1) Union Types
// --------------
object demoUnionTypes {

  // Let us assume:
  case class Red()
  case class Yellow()
  case class Green()

  // With these types defined, we translate the BSL comment into a
  // Scala type declaration.
  // Here the pipe operator `A | B` is called a "union type" and
  // is read as "A or B".
  type TrafficLight = Red | Yellow | Green

  // Exhaustivity checking for pattern matching will help us
  // to not forget one alternative.
  def switchTrafficLight(t: TrafficLight): TrafficLight = t match {
    case Red() => Green()
    case Yellow() => Red()
    case Green() => Yellow()
  }

  println(switchTrafficLight(Green())) // Yellow()
}

// 3) Subtyping
// ------------
object demoSubtypes {

  // Finally, we can use Scala's subtyping feature to express that "Red is a TrafficLight".
  sealed trait TrafficLight
  case class Red() extends TrafficLight
  case class Yellow() extends TrafficLight
  case class Green() extends TrafficLight

  def switchTrafficLight(t: TrafficLight): TrafficLight = t match {
    case Red() => Green()
    case Yellow() => Red()
    case Green() => Yellow()
  }

  println(switchTrafficLight(Green()))
}
