package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import manager.DrinkMachine;
import model.BeverageType;
import model.Ingredient;

public class MainApplication {

	public static void main(String[] args) {
		
		// Set up machine with outlets
		int outlets = 3;
		DrinkMachine drinkMachine = new DrinkMachine(outlets);
		
		// Add ingredient in machine with total quantity
		Map<Ingredient.Type, Integer> ingredientWithQty = getIngredientsWithQty();
		drinkMachine.addIngredientWithQty(ingredientWithQty);
		
		// Place order to prepared Hot Tea
		drinkMachine.prepareBeverage(BeverageType.HOT_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 200),
				new Ingredient(Ingredient.Type.HOT_MILK, 100),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 10),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 10),
				new Ingredient(Ingredient.Type.TEA_LEAVES_SYRUP, 30))
				);
		
		// Place order to prepared Hot Coffee
		drinkMachine.prepareBeverage(BeverageType.HOT_COFFEE, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 100),
				new Ingredient(Ingredient.Type.HOT_MILK, 400),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.TEA_LEAVES_SYRUP, 30))
				);
		
		// Place order to prepared Black Tea
		drinkMachine.prepareBeverage(BeverageType.BLACK_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 300),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.TEA_LEAVES_SYRUP, 30))
				);
		
		// Place order to prepared Green Tea
		drinkMachine.prepareBeverage(BeverageType.GREEN_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 100),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.GREEN_MIXTURE, 30))
				);
		
		sleep(30);
		
		System.out.println();
		// Get all ingredient list which are required to refill
		Set<String> ingredientWhoseHasZeroQuantity = drinkMachine.getAllIngredientWhichHasZeroQty();
		if (null != ingredientWhoseHasZeroQuantity && ingredientWhoseHasZeroQuantity.size() > 0) {
			System.out.println("Ingredient which required to refill : " + ingredientWhoseHasZeroQuantity);
		}
		
		System.out.println();
		
		// Add more quantity of ingredient hot water
		drinkMachine.addIngredientWithQty(Collections.singletonMap(Ingredient.Type.HOT_WATER, 500));
		System.out.println("More quantities are added of Hot Water ingredient");
		
		System.out.println();
		// Again place order to prepared Green Tea
		drinkMachine.prepareBeverage(BeverageType.GREEN_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 100),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.GREEN_MIXTURE, 30))
				);
		
		sleep(40);
		
		System.out.println();
		// Add new ingredient i.e Green mixture
		drinkMachine.addIngredientWithQty(Collections.singletonMap(Ingredient.Type.GREEN_MIXTURE, 500));
		System.out.println("Green mixture ingredient is added in drink machine");
		
		System.out.println();
		// Again place order to prepared Green Tea
		drinkMachine.prepareBeverage(BeverageType.GREEN_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 100),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.GREEN_MIXTURE, 30))
				);
		
		
		sleep(40);
		
		System.out.println();
		// Add more quantity of ingredient sugar syrup
		drinkMachine.addIngredientWithQty(Collections.singletonMap(Ingredient.Type.SUGAR_SYRUP, 50));
		System.out.println("More quantities are added of sugar syrup ingredient");
		
		System.out.println();
		// Again place order to prepared Green Tea
		drinkMachine.prepareBeverage(BeverageType.GREEN_TEA, Arrays.asList(
				new Ingredient(Ingredient.Type.HOT_WATER, 100),
				new Ingredient(Ingredient.Type.GINER_SYRUP, 30),
				new Ingredient(Ingredient.Type.SUGAR_SYRUP, 50),
				new Ingredient(Ingredient.Type.GREEN_MIXTURE, 30))
				);
				
	}
	
	private static Map<Ingredient.Type, Integer> getIngredientsWithQty() {
		Map<Ingredient.Type, Integer> ingredientWithQty = new HashMap<>();
		ingredientWithQty.put(Ingredient.Type.HOT_WATER, 500);
		ingredientWithQty.put(Ingredient.Type.HOT_MILK, 500);
		ingredientWithQty.put(Ingredient.Type.GINER_SYRUP, 100);
		ingredientWithQty.put(Ingredient.Type.SUGAR_SYRUP, 100);
		ingredientWithQty.put(Ingredient.Type.TEA_LEAVES_SYRUP, 100);
		return ingredientWithQty;
	}
	
	private static void sleep(final int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
		}
	}
}