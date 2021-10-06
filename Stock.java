
public class Stock {

	public static void main(String[] args) {
		Fruit appleObject = new Fruit(1, "Apple", 20, 100);
		 Fruit pineappleObject = new Fruit(2, "Pineapple", 50, 100);
		 Fruit mangoObject = new Fruit(3, "Mango", 30, 100);
	     Fruit papayaObject = new Fruit(4, "Papaya", 60, 100);
		 Fruit grapesObject = new Fruit(5, "Grapes", 40, 100);
		 Main.fruitObjectsList.add(appleObject);
		 Main.fruitObjectsList.add(pineappleObject);
		 Main.fruitObjectsList.add(mangoObject);
		 Main.fruitObjectsList.add(papayaObject);
		 Main.fruitObjectsList.add(grapesObject);
	}

	public void printStock() {
		System.out.println("Available fruits ");
		System.out.println("Fruit id   Fruit Name   Fruit Price   Available Quantity");
		for(Fruit element : Main.fruitObjectsList) {
			System.out.println(element.getId() + "   " + element.getName() + "         " + element.getPrice() + "        " + element.getQuantity());
		} 
	}
	
}
