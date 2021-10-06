import java.util.HashMap;
import java.util.Map;

public class Purchase {

	public void purchase(String username) {
		Map<Integer,Integer> cart=new HashMap<>();
		for(User element : Main.userObjectsList) {
			if(element.getUsername().equals(username)) {
				System.out.println("Your Purchase : ");
				element.printCart();
				cart=element.getUserCart();
				for (Map.Entry<Integer , Integer> entry : cart.entrySet()) {
					for (Fruit entry1 : Main.fruitObjectsList) {
					    if(entry.getKey() == entry1.getId()) {
					    	int oldQuantity=entry1.getQuantity();
					    	entry1.setQuantity(oldQuantity-entry.getValue());
					    }
					}
				}
				element.printBill();
				element.clearCart();
			}
		}
	}
}
