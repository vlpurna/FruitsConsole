import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static List<User> userObjectsList = new ArrayList<>();
	static List<Fruit> fruitObjectsList = new ArrayList<>();
	static Map<Integer, Integer> temporaryUserCart = new HashMap<>();

	public static void main(String[] args) {
		Main mainObject = new Main();
		Stock.main(null);
		mainObject.start();
	}

	public void start() {
		Main mainObject = new Main();
		System.out.println("Welcome !");
		System.out.print("Do you want to 1.Register 2.Login 3.Continue without login ");
		int userInput = scanner.nextInt();
		switch (userInput) {

		case 1:
			System.out.println("--------------------Registration-----------------------");
			mainObject.register();
			break;

		case 2:
			System.out.println("-----------------------Login--------------------------");
			mainObject.login();
			break;

		case 3: mainObject.addToCartWithoutLogin();
			break;

		default:
			System.out.println("Not in 1, 2 or 3");
		}
	}

	public void register() {
		Map<Integer, Integer> userCart = new HashMap<>();
		Main mainObject = new Main();
		boolean isUserExists = false;
		System.out.print("Enter Username : ");
		String registerUsername = scanner.next();
		System.out.print("Enter Password : ");
		String registerPassword = scanner.next();
		for (User element : userObjectsList) {
			if (element.getUsername().equals(registerUsername)) {
				isUserExists = true;
			}
		}
		if (isUserExists) {
			System.out.println("USER EXISTS !! ");
			mainObject.start();
		} else {
			User userObject = new User(registerUsername, registerPassword, userCart);
			userObjectsList.add(userObject);
			mainObject.start();
		}
	}

	public void login() {
		boolean isUserExists = false;
		Main mainObject = new Main();
		System.out.print("Enter Username : ");
		String loginUsername = scanner.next();
		System.out.print("Enter Password : ");
		String loginPassword = scanner.next();
		for (User element : userObjectsList) {
			if (element.getUsername().equals(loginUsername) && element.getPassword().equals(loginPassword)) {
				isUserExists = true;
			}
		}
		if (isUserExists) {
			System.out.println("Logged In !!");
			mainObject.addToCart(loginUsername);
		} else {
			System.out.println("Enter right credentials !! ");
			mainObject.start();
		}
	}

	public void addToCart(String username) {
		Map<Integer, Integer> cart = new HashMap<>();
		Map<Integer, Integer> cart1 = new HashMap<>();
		Main mainObject = new Main();
		Purchase purchaseObject=new Purchase();
		boolean isCartEmpty = true;
		for (User element : userObjectsList) {
			if (element.getUsername().equals(username) && element.getUserCart().isEmpty()) {
				isCartEmpty = false;
			}
		}
		if (!isCartEmpty) {
			System.out.println("Your cart is empty");
		} else {
			System.out.println("Your Cart : ");
			for (User element : userObjectsList) {
				if (element.getUsername().equals(username)) {
					for (Map.Entry<Integer, Integer> entry : element.getUserCart().entrySet()) {
						for (Fruit elements : fruitObjectsList) {
							if (elements.getId() == entry.getKey()) {
								System.out.println(elements.getName() + " - " + entry.getValue());
							}
						}
					}
				}
			}
		}
		System.out.print("Do you want to 1.Add Fruits 2.Purchase 3.Logout");
		int userInput = scanner.nextInt();
		if (userInput == 1) {
			cart = mainObject.addFruits();
			for (Map.Entry<Integer , Integer > entry : cart.entrySet()) {
			    cart1.put(entry.getKey(), entry.getValue());
			}
			for(User element : userObjectsList) {
				if(element.getUsername().equals(username)) {
					element.setUserCart(cart1);
				}
			}
			temporaryUserCart.clear();
			System.out.println("Your Cart : ");
			for (User element : userObjectsList) {
				if (element.getUsername().equals(username)) {
					for (Map.Entry<Integer, Integer> entry : element.getUserCart().entrySet()) {
						for (Fruit elements : fruitObjectsList) {
							if (elements.getId() == entry.getKey()) {
								System.out.println(elements.getName() + " - " + entry.getValue());
							}
						}
					}
				}
			}
			System.out.println("Do you want to 1.Purchase 2.Logout : ");
			int userInput1=scanner.nextInt();
			if(userInput1 ==1 ) {
				//go to purchase page
				purchaseObject.purchase(username);
				System.out.print("Do you want to 1.Continue 2.Logout");
				int input=scanner.nextInt();
				if(input==1) {
					mainObject.addToCart(username);
				}
				else {
					System.out.println("Logged out ! ");
					mainObject.start();
				}
			}
			else {
				System.out.println("Logged out !! ");
			mainObject.start();
			}
		} else if (userInput == 2) {
			// Go to purchase page
			purchaseObject.purchase(username);
			System.out.print("Do you want to 1.Continue 2.Logout");
			int inp=scanner.nextInt();
			if(inp==1) {
				mainObject.addToCart(username);
			}
			else {
				System.out.println("Logged out ! ");
				mainObject.start();
			}
			
		} else {
			System.out.println("Logged out !! ");
			mainObject.start();
		}

	}

	public Map<Integer, Integer> addFruits() {
		boolean isQuantityRight=false;
		boolean isItemExists=false;
		int oldQuantity=0;
		Stock stockObject = new Stock();
		stockObject.printStock();
		System.out.print("Enter Fruit id : ");
		int fruitId = scanner.nextInt();
		if (fruitId > 0 && fruitId < 6) {
			System.out.print("Enter quantity : ");
			int quantity = scanner.nextInt();
			for (Fruit element : fruitObjectsList) {
				if (element.getId() == fruitId && element.getQuantity() >= quantity) {
					isQuantityRight=true;
				}
			}
			if(isQuantityRight) {
				for (Map.Entry<Integer , Integer > entry : temporaryUserCart.entrySet()) {
				    if(entry.getKey() == fruitId ) {
				    	isItemExists=true;
				    	oldQuantity=entry.getValue();
				    }
				}
				if(isItemExists) {
				temporaryUserCart.put(fruitId, quantity);
				}
				else {
					temporaryUserCart.put(fruitId, quantity + oldQuantity);
				}
				System.out.println("Do you want to add more items ? 1.Yes 2.No " );
				int userInput=scanner.nextInt();
				if(userInput==1) {
					addFruits();
				}
				else {
					return temporaryUserCart;
				}
			}
			else {
				System.out.println("Enter right quantity");
				addFruits();
			}
		} else {
			System.out.println("Enter correct id !! ");
			addFruits();
		}
		return temporaryUserCart;
	}
	
	public void addToCartWithoutLogin(){
		Purchase purchaseObject=new Purchase();
		Main mainObject = new Main();
		boolean isUserExists = false;
		Map<Integer, Integer> cart = new HashMap<>();
		Map<Integer, Integer> cart1 = new HashMap<>();
		cart = mainObject.addFruits();
		for (Map.Entry<Integer , Integer > entry : cart.entrySet()) {
		    cart1.put(entry.getKey(), entry.getValue());
		}
		
		temporaryUserCart.clear();
		System.out.print("Enter Username : ");
		String loginUsername = scanner.next();
		System.out.print("Enter Password : ");
		String loginPassword = scanner.next();
		for (User element : userObjectsList) {
			if (element.getUsername().equals(loginUsername) && element.getPassword().equals(loginPassword)) {
				isUserExists = true;
			}
		}
		if (isUserExists) {
			System.out.println("Fruits Added to cart !");
			for(User element : userObjectsList) {
				if(element.getUsername().equals(loginUsername)) {
					element.setUserCart(cart1);
				}
			}
			System.out.print("Do you want to 1.Purchase 2.Logout");
			int input=scanner.nextInt();
			if(input == 1) {
				purchaseObject.purchase(loginUsername);
				System.out.print("Do you want to 1.Continue 2.Logout");
				int inp=scanner.nextInt();
				if(inp==1) {
					mainObject.addToCart(loginUsername);
				}
				else {
					System.out.println("Logged out ! ");
					mainObject.start();
				}
			}
			else {
				mainObject.start();
			}
		} else {
			System.out.println("Enter right credentials !! ");
			mainObject.start();
		}
	}
}
