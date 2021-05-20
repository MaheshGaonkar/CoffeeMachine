package model;

public class Ingredient {
	private Type name;
	private int qty;

	public Ingredient(final Type name, final int qty) {
		this.name = name;
		this.qty = qty;
	}

	public Type getName() {
		return name;
	}

	public int getQty() {
		return qty;
	}

	public void reduceQty(final int qtyToReduce) {
		qty = qty - qtyToReduce;
	}
	
	public void addQty(final int qtyToAdd) {
		qty += qtyToAdd;
	}
	
	public enum Type {
		HOT_WATER("hot_water"),
		HOT_MILK("hot_milk"),
		GINER_SYRUP("ginger_syrup"),
		SUGAR_SYRUP("sugar_syrup"),
		TEA_LEAVES_SYRUP("tea_leaves_syrup"),
		GREEN_MIXTURE("green_mixture");
		
		private String value;
		
		Type(final String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
}