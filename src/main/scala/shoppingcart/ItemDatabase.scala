package shoppingcart


object ItemDatabase {

  var allItems:Seq[Item] = Seq(
    Item("Laptop", 1000.0, Set("Electronics", "Computers")),
    Item("Apple", 0.5, Set("Groceries", "Fruit")),
    Item("Banana", 0.3, Set("Groceries", "Fruit"))
  )

  //intersect - comparing items.categories have any matches with all categories
  def getRecommendations(categories: Set[String]): Seq[Item]={
    allItems.filter(item => item.categories.intersect(categories).nonEmpty)
  }

  def main(args:Array[String]): Unit ={
    val shoppingCart = new ShoppingCart()
    shoppingCart.addItem(Item("apple", 3.0,Set("Fruit")), 1)
    shoppingCart.addItem(Item("Orange", 4.0,Set("Color")), 2)
    println(s"Total cost: ${shoppingCart.totalCost()}")
    println(s"applied discount: ${shoppingCart.applyDiscount(10)}")
    shoppingCart.addToWishList(Item("apple", 3.0,Set("Fruit")))
    shoppingCart.addToWishList(Item("Orange", 4.0,Set("Color")))
    shoppingCart.addToWishList(Item("Orange", 4.0,Set("Color")))
    val wish =shoppingCart.getWishListNames()
    println(s"wish list: ${wish.mkString(", ")}")
    val recommendations = shoppingCart.recommendedItems()
    println("Recommended items:")
    recommendations.foreach(item =>println(s" ${item.categories.mkString(",")}"))
    shoppingCart.recordOrder()
    //Order:Map(Item(apple,3.0,Set(Fruit)) -> 1, Item(Orange,4.0,Set(Color)) -> 2) Total Cost:11.0
    shoppingCart.getOrderHistory.foreach{
      order => order.items.foreach{
      case(item,quantity) => println(s"${item.name},Quantity: ${quantity}")
      }
    }
    }
}
