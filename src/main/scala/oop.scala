package demo


// Objects
// =======
// OOP is about objects, so what is an object?


object fromJava {
    //var effectiveJava = new Object() {
    //  String title = "Effective Java";
    //  String author = "Joshua Bloch";
    //  boolean isBorrowed = false;
    //  void borrow() {
    //    if (isBorrowed)
    //      System.out.println("Cannot borrow book again: " + title);
    //    else
    //      isBorrowed = true;
    //  }
    //};

    // Cf. slide 12
    val effectiveJava = new Object {
      val title = "Effective Java"
      val author = "Joshua Bloch"

      var isBorrowed = false

      def borrow(): Unit =
        if isBorrowed then println("Cannot borrow book again: " + title)
        else isBorrowed = true
    }


    // Cf. slide 16
    class Book1(_title: String, _author: String) {
      val title = _title
      val author = _author
      var isBorrowed = false

      def borrow(): Unit =
        if isBorrowed then println("Cannot borrow book again: " + title)
        else isBorrowed = true
    }

    class Book2(val title: String, val author: String) {
      var isBorrowed = false

      def borrow(): Unit =
        if isBorrowed then println("Cannot borrow book again: " + title)
        else isBorrowed = true
    }

    val book1 = new Book1("Effective Java", "Joshua Bloch")
    val book2 = Book1("Effective Java", "Joshua Bloch")
    val book3 = Book2("Effective Java", "Joshua Bloch")


    // field access:
    println(book3.title)
    println(book3.isBorrowed)

    book3.borrow()
    book3.borrow()

    // Like in Java: comparison for classes is by "reference equality" unless defined otherwise
    println(book1 == book2)
    println(book1 == book1)


}







object demoObjects {

  object myAccount {
    // balance is called a "mutable field"
    private var currentBalance = 10
    // deposit is called a "method"
    def deposit(amount: Int) = currentBalance += amount
    def balance(): Int = currentBalance
  }

  // So, what is the essence of an object?
  // we can call methods on it:
  myAccount.deposit(20)

  // this changes the internal state of the object.
  // we can also trigger observation on the object.
  println(myAccount.balance())

  // create a second object
  object myRichAccount {
    private var currentBalance = 10000
    def deposit(amount: Int) = currentBalance += amount
    def balance(): Int = currentBalance
  }

  // changing the internal state of one object, does not (necessarily) affect the other
  myRichAccount.deposit(1000)
  println(myRichAccount.balance())
  println(myAccount.balance())

  // objects (often) have an identity
  println(myAccount == myAccount)
  println(myAccount == myRichAccount)
}

object demoClasses {

  // Classes
  // =======
  // We "could" write a function that creates objects. This is very much NOT encouraged.
  // The return type of `createAccount` is `Object` and every information about it
  // having two methods (deposit and currentBalance) is lost. We can construct objects this
  // way, but we can never use them again... You can try in the console:
  // scala> val o = createAccount(20)
  // scala > o.currentBalance()
  //   --[E008] Not Found Error: -----------------------------------------------------
  //   1 | o.currentBalance()
  //     |^^^^^^^^^^^^^^^^
  //     | value currentBalance is not a member of Object
  def createAccount(initialBalance: Int) =
    object newAccount {
      private var balance = 10
      def deposit(amount: Int) = balance += amount
      def currentBalance(): Int = balance
    }
    newAccount


  // Instead, we use classes as blueprints (as you know them from Info 2)
  class SavingsAccount(initialBalance: Int) {
    private var balance = initialBalance
    def deposit(amount: Int) = balance += amount
    def currentBalance(): Int = balance
  }

  // usage of objects, again
  val savings = new SavingsAccount(0)

  // ... usage ...
  println(savings.currentBalance())


  // One HUGE advantage of classes over functions like `createAccount` is, that we can
  // add new features, later...

  // inheritance
  class CheckingsAccount(initialBalance: Int) extends SavingsAccount(initialBalance) {
    def withdraw(amount: Int) = deposit(amount * -1)
  }

  // or modify existing behavior
  class LoggingAccount(initialBalance: Int) extends SavingsAccount(initialBalance) {
    // this is called an "Override" -- please do NOT confuse it with overloading.
    override def deposit(amount: Int) =
      println("Depositing " + amount)
      super.deposit(amount)
  }

  // this way the implementations SHARE common behavior
  val checking = new CheckingsAccount(10)
  println(checking.currentBalance())
  // but also can have additional, individual behavior
  println(checking.withdraw(2))
  println(checking.currentBalance())

  println(checking.withdraw(10))

  // we could also have different implementations of the same features
  class TaxAccount(initialBalance: Int) {
    private var transactions = List(initialBalance)
    def deposit(amount: Int) = transactions = amount +: transactions
    def balance(): Int = transactions.sum
    // and additional method
    def history(): List[Int] = transactions
  }

  // importantly, we can now store the different objects in one list:
  val accounts = List(savings, checking, new LoggingAccount(10))

  // and we can go over the list and deposit 10 Eur each
  accounts.foreach { a => a.deposit(10) }
}

// BUT HOW WOULD WE EVER WRITE A FUNCTION THAT EXPECTS a SavingsAccount or a TaxAccount?
// They look similar...
// We need to somehow say what is the minimal set of features / observations we require...

// Note that this is in contrast to Info 1:
// - Domain modeling in Info 1 (and FP) is about "what do things *consists* of?"
// - Domain modeling in OOP is about "what do things allow me to observe?"

// Interfaces
// ==========
object demoInterfaces {

  trait Account {
    def deposit(amount: Int): Unit
    def balance(): Int
  }

  // Same definitions as above:

  class SavingsAccount(initialBalance: Int) extends Account {
    private var currentBalance = initialBalance
    def deposit(amount: Int) = currentBalance += amount
    def balance(): Int = currentBalance
  }

  class TaxAccount(initialBalance: Int) extends Account {
    private var transactions = List(initialBalance)
    def deposit(amount: Int) = transactions = amount +: transactions
    def balance(): Int = transactions.sum
    def history(): List[Int] = transactions
  }


  def transfer(amount: Int, from: Account, to: Account): Unit =
    from.deposit(amount * -1)
    to.deposit(amount)

}
