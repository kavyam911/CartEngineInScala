package shoppingcart

import shoppingcart.ItemDatabase.allItems

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

case class Item(name: String, price: Double, categories: Set[String])
case class Order(items:Map[Item,Int], totalCost: Double)

class ShoppingCart {
  private var items:Map[Item,Int] = Map()
  private var purchaseHistory:Vector[Order] = Vector()
  private var wishList:ListBuffer[Item] = ListBuffer()
  private var promotions:ArrayBuffer[String] = ArrayBuffer()


  def addItem(item: Item, quantity: Int): Unit ={
    items = items.updated(item, items.getOrElse(item,0) + quantity)
    //purchaseHistory=purchaseHistory :+item
    allItems = allItems:+item

  }

  def addToWishList(item: Item):Unit ={
    wishList +=item
  }

  def getWishListNames() : Seq[String] ={
    wishList.map(_.name).toSet.toSeq
  }

  def promotions(promo: String): Unit ={
    promotions +=promo
  }
/*After groupBy(_.categories), you would have:
{"Electronics", "Audio"} -> Seq(Item A, Item B)
{"Groceries", "Fruit"} -> Seq(Item C)
After map(_._2.head), you would have:  Seq(Item A, Item C)
Here, only the first item from each group is taken, removing duplicates based on categories.
 */
  def recommendedItems(): Seq[Item] = {
    val allCategories = items.keys.flatMap(_.categories).toSet
    val recommendations = ItemDatabase.getRecommendations(allCategories)
    recommendations.groupBy(_.categories).map(_._2.head).toSeq
  }

  def totalCost(): Double = {
    items.map {
      case(item,quantity) => item.price*quantity
    }.sum
  }

  def applyDiscount(discountPercentage: Double): Double ={
    val total = totalCost()
    if(total > 100) total *(1-discountPercentage/100) else total
  }

  def recordOrder(): Unit ={
    val order = Order(items, totalCost())
    purchaseHistory = purchaseHistory:+order
  }

  def getOrderHistory: Vector[Order] = purchaseHistory

}
