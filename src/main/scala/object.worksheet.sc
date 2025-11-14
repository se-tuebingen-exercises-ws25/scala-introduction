class Book(val title: String, val author: String) {
  private var isBorrowed = false

  def borrow() = {
    this.checkBorrowed()
    if (!isBorrowed) {
      isBorrowed = true
    }
  }

  def checkBorrowed(): Unit = {
    if (isBorrowed) {
      println("Cannot borrow book again")
    }
  }

  def borrowed: Boolean = isBorrowed
}
object Book {
  def apply(title: String): Book = new Book(title, "Joshua Bloch")
  def apply(title: String, author: String) = new Book(title, author)
}

Book("Effective Scala")

//
//val effectiveJava = Book("Effective Java", "Joshua Bloch")
//
//effectiveJava.title
//
//val effectiveJavaSecondEdition = Book(
//  "Effective Java (2nd Ed)",
//  "Joshua Bloch"
//)

//object effectiveJava extends Book {
//  val title = "Effective Java"
//  val author = "Joshua Bloch"
//}
//
//object effectiveJavaSecondEdition extends Book {
//  val title = "Effective Java (2nd Ed)"
//  val author = "Joshua Bloch"
//}








//import collection.immutable.Set
//
//val s = Set[Int | Boolean](1, 2, true, 3)
//
//println(s)
//
//val s2 = s + false
//
//val isTrue = s.contains(true)
//println(true)
//
//s2.foreach { el => el match {
//  case n: Int => println(s"Number: ${n}")
//  case b: Boolean => println(s"Boolean: ${b}")
//}
//}
//
//


type Cents = Int

trait Account {
  // def withdraw(amount: Int): Unit
  def deposit(amount: Cents): Unit
  def getBalance: Cents
}

trait Fees {
  def fees(): Int = 1000
}

trait A {
  def a() = b() + 1 // provided
  def b(): Int      // required
}

trait B {
  def b() = 17      // provided
}

// Mixin Composition
class C extends A, B

val c = new C

c.a()

def f(a: Int) = println(a)

f(42)

//f(null)

def loop(): Nothing = throw new Exception()

f(???)

val s: SavingsAccount = ???


def p(x: Int) =
  println(x + 1)

p(1)
p(2)


enum ListInt {
  case IntNil()
  case IntCons(el: Int, rest: ListInt)
}

enum ListString {
  case StringNil()
  case StringCons(el: String, rest: ListString)
}

enum List[T] {
  case Nil()
  case Cons(el: T, rest: List[T])
}

import ListInt.*
import ListString.*
import List.*

def map[A, B](l: List[A])(f: A => B): List[B] = l match {
  case List.Nil() => Nil()
  case List.Cons(el, rest) => Cons(f(el), map[A, B](rest)(f))
}
//
//
//
//
//val l11: List[Int] = Cons(1, Nil())
//
//map[Int, Boolean](l11)(n => n > 0)
//
//val l12 = StringCons("test", StringNil())
//



//A <: B
//B <: C
//-------
//A <: C
//
//A <: A
//
//A extends B
//-----------
//A <: B
//
//A <: Any
//
//Nothing <: A

// SavingsAccount <: Account
// SavingsAccount <: Fees
abstract class SavingsAccount(initialBalance: Cents) extends Account, Fees {
  protected var balance = initialBalance
  def deposit(amount: Cents) = balance += amount
  def getBalance: Cents = balance - fees()
}

// DoubleAccount <: SavingsAccount
class DoubleAccount(initialBalance: Cents) extends SavingsAccount(initialBalance) {
  def double() = balance = (balance + balance) - fees()
  override def fees() = 150
}

class HalfingAccount(initialBalance: Cents) extends SavingsAccount(initialBalance) {
  def computeTaxes() = balance = (balance / 2) - fees()
  //def fees() = 0
}

// DoubleAccount <: SavingsAccount <: Account

def user(a: SavingsAccount) =
  a.deposit(10)
  a.deposit(20)
  println(a.getBalance)

//def other(a: Account) =
//  user(a)

val acc = new DoubleAccount(100)
user(acc)
acc.double()
acc.getBalance













