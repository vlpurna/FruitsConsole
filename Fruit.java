public class Fruit {

	Main mainObject=new Main();
	
	private Integer id;
	private String name;
	private Integer price;
	private Integer quantity;

	public Fruit(Integer id, String name, Integer price, Integer quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer newQuantity) {
		this.quantity = newQuantity;
	}

	
		
}
