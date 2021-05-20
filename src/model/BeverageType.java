package model;

public enum BeverageType {
	HOT_TEA("hot_tea"),
	HOT_COFFEE("hot_coffee"),
	BLACK_TEA("black_tea"),
	GREEN_TEA("green_tea");
	
	private String value;
	
	private BeverageType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}