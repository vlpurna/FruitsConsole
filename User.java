import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User {
	
	private String username;
	private String password;
	private Map<Integer,Integer> userCart = new HashMap<>();
	
	public User(String username , String password , Map<Integer,Integer> userCart) {
		this.username=username;
		this.password=password;
		this.userCart=userCart;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public Map<Integer,Integer> getUserCart(){
		return this.userCart;
	}
	public void setUserCart(Map<Integer,Integer> cart){
		for (Map.Entry<Integer , Integer> entry : cart.entrySet()) {
		    this.userCart.put(entry.getKey(),entry.getValue());
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void printCart() {
		for (Map.Entry<Integer , Integer > entry : userCart.entrySet()) {
			for(Fruit element : Main.fruitObjectsList) {
				if(entry.getKey() == element.getId())
		    System.out.println(element.getName() + "-" + entry.getValue());
			}
		}
	}
	
	public void clearCart() {
		this.userCart.clear();
	}
	
	public void printBill() {
		try (FileWriter myWriter = new FileWriter("filename.txt")) {
			int totalPrice = 0;
			myWriter.write("Bill : \n");

			for (Map.Entry<Integer, Integer> entry : this.userCart.entrySet()) {
				for (Fruit element1 : Main.fruitObjectsList) {
					if (element1.getId() == entry.getKey()) {
						myWriter.write(element1.getName() + " - " + (element1.getPrice() * entry.getValue()) + "/- \n");
						totalPrice = totalPrice + (element1.getPrice() * entry.getValue());
						break;
					}
				}

			}
			myWriter.write("Total Price : " + totalPrice + "/-");
			System.out.println("Successfully wrote bill to the file.");
			System.out.println("Thank you for the purchase ! ");
		}

		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
